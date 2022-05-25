package com.wbh.emall.user.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wbh.emall.user.common.entity.EmallGoods;
import com.wbh.emall.user.common.entity.dto.EmallGoodsDto;
import com.wbh.emall.user.common.entity.vo.EmallGoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author WBH
 * @since 2022/2/20
 */
public interface EmallGoodsMapper extends BaseMapper<EmallGoods> {
    
    List<EmallGoodsVo> selectGoodsCoverByType(@Param("typeId") Integer typeId);
    
    IPage<EmallGoodsVo> selectGoodsByPage(IPage<?> page, @Param("uId") Integer uId);
    
    EmallGoodsVo selectSimpleGoodsById(@Param("goodsId") Integer goodsId);
}