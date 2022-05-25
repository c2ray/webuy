package com.wbh.emall.user.api.controller;

import com.wbh.emall.common.entity.Result;
import com.wbh.emall.common.exception.AuthenticationException;
import com.wbh.emall.common.util.EncryptUtil;
import com.wbh.emall.common.util.Objects;
import com.wbh.emall.user.common.entity.EmallGoods;
import com.wbh.emall.user.common.entity.EmallGoodsCover;
import com.wbh.emall.user.common.entity.EmallGoodsPic;
import com.wbh.emall.user.common.entity.vo.EmallGoodsCoverVo;
import com.wbh.emall.user.common.entity.vo.EmallGoodsPicVo;
import com.wbh.emall.user.common.entity.vo.EmallGoodsVo;
import com.wbh.emall.user.common.service.EmallGoodsCoverService;
import com.wbh.emall.user.common.service.EmallGoodsPicService;
import com.wbh.emall.user.common.service.EmallGoodsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * (emall_goods)表控制层
 *
 * @author xxxxx
 */
@Slf4j
@RestController
@RequestMapping("/api/goods")
public class EmallGoodsController {
    private final EmallGoodsService emallGoodsService;
    private final EmallGoodsPicService emallGoodsPicService;
    private final EmallGoodsCoverService emallGoodsCoverService;
    
    public EmallGoodsController(EmallGoodsPicService emallGoodsPicService, EmallGoodsService emallGoodsService, EmallGoodsCoverService emallGoodsCoverService) {
        this.emallGoodsPicService = emallGoodsPicService;
        this.emallGoodsService = emallGoodsService;
        this.emallGoodsCoverService = emallGoodsCoverService;
    }
    
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("/{id}")
    public Result<EmallGoodsVo> getGoods(@PathVariable Integer id) {
        EmallGoodsVo emallGoodsVo = new EmallGoodsVo();
        EmallGoods goods = emallGoodsService.getById(id);
        Integer gid = goods.getId();
        // 获取商品封面
        Integer coverId = goods.getCoverId();
        EmallGoodsCoverVo emallGoodsCoverVo = new EmallGoodsCoverVo();
        BeanUtils.copyProperties(emallGoodsCoverService.getById(coverId), emallGoodsCoverVo);
        // 获取商品图片
        List<EmallGoodsPicVo> goodsPics = emallGoodsPicService.selectPicListByGid(gid);
        BeanUtils.copyProperties(goods, emallGoodsVo);
        emallGoodsVo.setCoverImg(emallGoodsCoverVo);
        emallGoodsVo.setImgs(goodsPics);
        return Result.success(emallGoodsVo);
    }
    
    /**
     * 根据商品类型获取商品列表信息
     *
     * @return 商品列表信息
     */
    @ApiOperation("根据商品类型获取商品封面信息")
    @GetMapping("/type/{type}/list")
    public Result<List<EmallGoodsVo>> getGoodsListByType(@PathVariable Integer type) {
        return Result.success(emallGoodsService.selectGoodsCoverByType(type));
    }
    
}
