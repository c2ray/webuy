package com.wbh.emall.user.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wbh.emall.user.common.entity.EmallGoodsOrderRelation;

import java.util.List;

/**
 * @author WBH
 * @since 2022/2/25
 */
public interface EmallGoodsOrderRelationMapper extends BaseMapper<EmallGoodsOrderRelation> {
    List<EmallGoodsOrderRelation> selectByOrderId(Integer orderId);
}