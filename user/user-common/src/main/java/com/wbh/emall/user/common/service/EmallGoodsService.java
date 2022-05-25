package com.wbh.emall.user.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbh.emall.user.common.entity.EmallGoods;
import com.wbh.emall.user.common.entity.EmallGoodsCover;
import com.wbh.emall.user.common.entity.dto.EmallGoodsDto;
import com.wbh.emall.user.common.entity.vo.EmallGoodsCoverVo;
import com.wbh.emall.user.common.entity.vo.EmallGoodsPicVo;
import com.wbh.emall.user.common.entity.vo.EmallGoodsVo;
import com.wbh.emall.user.common.mapper.EmallGoodsMapper;
import com.wbh.emall.user.common.mapper.EmallGoodsPicMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WBH
 * @since 2022/2/20
 */
@Service
public class EmallGoodsService extends ServiceImpl<EmallGoodsMapper, EmallGoods> implements IService<EmallGoods> {
    
    private final EmallGoodsMapper emallGoodsMapper;
    private final EmallGoodsPicService emallGoodsPicService;
    private final EmallGoodsCoverService emallGoodsCoverService;
    
    public EmallGoodsService(EmallGoodsMapper emallGoodsMapper, EmallGoodsPicMapper emallGoodsPicMapper, EmallGoodsPicService emallGoodsPicService, EmallGoodsCoverService emallGoodsCoverService) {
        this.emallGoodsMapper = emallGoodsMapper;
        this.emallGoodsPicService = emallGoodsPicService;
        this.emallGoodsCoverService = emallGoodsCoverService;
    }
    
    public List<EmallGoodsVo> selectGoodsCoverByType(Integer typeId) {
        return emallGoodsMapper.selectGoodsCoverByType(typeId);
    }
    
    public IPage<EmallGoodsVo> selectGoodsByPage(Integer userid, Integer current, Integer size) {
        IPage<EmallGoodsVo> emallGoodsDtoIPage = emallGoodsMapper.selectGoodsByPage(new PageDTO<>(current, size),
                userid);
        List<EmallGoodsVo> goodsVoList = emallGoodsDtoIPage.getRecords();
        goodsVoList = goodsVoList.stream().peek(goods -> {
            Integer coverId = goods.getCoverId();
            EmallGoodsCover emallGoodsCover = emallGoodsCoverService.getById(coverId);
            EmallGoodsCoverVo emallGoodsCoverVo = new EmallGoodsCoverVo();
            BeanUtils.copyProperties(emallGoodsCover, emallGoodsCoverVo);
            goods.setCoverImg(emallGoodsCoverVo);
            // 获取商品图片
            Integer gid = goods.getId();
            List<EmallGoodsPicVo> imgs = emallGoodsPicService.selectPicListByGid(gid);
            goods.setImgs(imgs);
        }).collect(Collectors.toList());
        emallGoodsDtoIPage.setRecords(goodsVoList);
        return emallGoodsDtoIPage;
    }
    
    public EmallGoodsVo selectSimpeGoodsById(Integer goodsId) {
        return emallGoodsMapper.selectSimpleGoodsById(goodsId);
    }
    
}


