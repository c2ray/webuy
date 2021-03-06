package com.wbh.emall.euser.api.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wbh.emall.common.constants.GlobalConstant;
import com.wbh.emall.common.entity.Result;
import com.wbh.emall.common.entity.rpc.PicCheckerDto;
import com.wbh.emall.common.exception.AuthenticationException;
import com.wbh.emall.common.util.EncryptUtil;
import com.wbh.emall.common.util.Objects;
import com.wbh.emall.user.common.entity.EmallGoods;
import com.wbh.emall.user.common.entity.EmallGoodsCover;
import com.wbh.emall.user.common.entity.EmallGoodsPic;
import com.wbh.emall.user.common.entity.dto.EmallGoodsDto;
import com.wbh.emall.user.common.entity.dto.EmallGoodsUpdateDto;
import com.wbh.emall.user.common.entity.vo.EmallGoodsCoverVo;
import com.wbh.emall.user.common.entity.vo.EmallGoodsPicVo;
import com.wbh.emall.user.common.entity.vo.EmallGoodsVo;
import com.wbh.emall.user.common.service.EmallGoodsCoverService;
import com.wbh.emall.user.common.service.EmallGoodsPicService;
import com.wbh.emall.user.common.service.EmallGoodsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author WBH
 * @since 2022/3/12
 */
@Slf4j
@RestController
@RequestMapping("/api/euser/goods")
public class EmallGoodsController {
    
    private static final String IMG_CHECK_URL = GlobalConstant.PREFIX_URL + "/api/task/img/check";
    
    final OkHttpClient client;
    private final EmallGoodsService emallGoodsService;
    private final EmallGoodsPicService emallGoodsPicService;
    private final EmallGoodsCoverService emallGoodsCoverService;
    
    public EmallGoodsController(EmallGoodsPicService emallGoodsPicService, EmallGoodsService emallGoodsService, EmallGoodsCoverService emallGoodsCoverService, OkHttpClient client) {
        this.emallGoodsPicService = emallGoodsPicService;
        this.emallGoodsService = emallGoodsService;
        this.emallGoodsCoverService = emallGoodsCoverService;
        this.client = client;
    }
    
    
    @ApiOperation("????????????")
    @PostMapping
    public Result<String> addGoods(@RequestHeader("Authorization") String token,
                                   @RequestBody EmallGoodsDto goods) throws AuthenticationException,
                                                                            IllegalAccessException {
        // ????????????
        int id = EncryptUtil.decryptId(token);
        Objects.assertFieldsFilled(goods,
                EmallGoodsVo.Fields.coverImg,
                EmallGoods.Fields.name,
                EmallGoods.Fields.desc,
                EmallGoods.Fields.originalPrice,
                EmallGoods.Fields.price,
                EmallGoods.Fields.typeId,
                EmallGoods.Fields.unitId);
        goods.setUId(id);
        log.info("????????????: {}", goods);
        // ??????????????????
        EmallGoodsCover goodsCover = new EmallGoodsCover();
        goodsCover.setUrl(goods.getCoverImg());
        // ??????????????????
        emallGoodsCoverService.save(goodsCover);
        Integer coverId = goodsCover.getId();
        goods.setCoverId(coverId);
        // ????????????
        emallGoodsService.save(goods);
        // ??????????????????
        List<String> imgs = goods.getImgs();
        List<EmallGoodsPic> goodsPics = imgs.stream().map(img -> {
            EmallGoodsPic pic = new EmallGoodsPic();
            pic.setGoodsId(goods.getId());
            pic.setUrl(img);
            return pic;
        }).collect(Collectors.toList());
        emallGoodsPicService.saveBatch(goodsPics);
        
        goodsPics.forEach(goodsPic -> {
            PicCheckerDto picCheckerDto = new PicCheckerDto();
            picCheckerDto.setPicId(goodsPic.getId());
            picCheckerDto.setImgUrl(goodsPic.getUrl());
            String picCheckDtoJson = JSON.toJSONString(picCheckerDto);
            okhttp3.RequestBody reqBody = okhttp3.RequestBody.create(MediaType.get("application/json"), picCheckDtoJson);
            Request request = new Request.Builder().url(IMG_CHECK_URL).post(reqBody).build();
            Call call = client.newCall(request);
            try (Response res = call.execute()) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return Result.success("????????????!", "????????????!");
    }
    
    @ApiOperation("??????????????????")
    @GetMapping
    public Result<IPage<EmallGoodsVo>> getGoods(@RequestHeader("Authorization") String token, Integer size, Integer current)
            throws AuthenticationException {
        int id = EncryptUtil.decryptId(token);
        return Result.success(emallGoodsService.selectGoodsByPage(id, current, size));
    }
    
    @ApiOperation("????????????")
    @PutMapping
    public Result<String> updateGoods(@RequestHeader("Authorization") String token,
                                      @RequestBody EmallGoodsUpdateDto goods) throws AuthenticationException,
                                                                                     IllegalAccessException {
        // ????????????
        int id = EncryptUtil.decryptId(token);
        Objects.assertFieldsFilled(goods,
                EmallGoodsVo.Fields.coverImg,
                EmallGoods.Fields.name,
                EmallGoods.Fields.desc,
                EmallGoods.Fields.originalPrice,
                EmallGoods.Fields.price,
                EmallGoods.Fields.typeId,
                EmallGoods.Fields.unitId);
        goods.setUId(id);
        log.info("????????????: {}", goods);
        // ????????????, ????????????????????? ????????????
        emallGoodsService.update(goods, new QueryWrapper<EmallGoods>()
                .eq("u_id", id).eq("id", goods.getId()));
        // ????????????
        List<EmallGoodsPic> imgsToDelete = goods.getDeleteImgs().stream()
                .filter(item -> item.getId() != null)
                .map(EmallGoodsPic.class::cast)
                .collect(Collectors.toList());
        emallGoodsPicService.removeBatchByIds(imgsToDelete);
        // ??????????????????
        EmallGoodsCoverVo coverImg = goods.getCoverImg();
        // ??????????????????
        emallGoodsCoverService.saveOrUpdate(coverImg);
        // ??????????????????
        List<EmallGoodsPicVo> imgs = goods.getImgs();
        List<EmallGoodsPic> goodsPics = imgs.stream()
                .map(EmallGoodsPic.class::cast)
                .filter(emallGoodsPic -> emallGoodsPic.getId() == null)
                .peek(goodsPic -> goodsPic.setGmtCreate(null))
                .collect(Collectors.toList());
        emallGoodsPicService.saveBatch(goodsPics);
        return Result.success("????????????!", "????????????!");
    }
    
    @ApiOperation("????????????")
    @DeleteMapping("/{id}")
    public Result<String> deleteGoods(@RequestHeader("Authorization") String token,
                                      @PathVariable("id") Integer gid) throws AuthenticationException {
        // ????????????
        int id = EncryptUtil.decryptId(token);
        emallGoodsService.removeById(gid);
        return Result.success("????????????!", "????????????!");
    }
}
