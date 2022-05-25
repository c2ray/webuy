package com.wbh.emall.user.common.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbh.emall.user.common.entity.EmallGoodsOrderRelation;
import com.wbh.emall.user.common.entity.EmallOrder;
import com.wbh.emall.user.common.entity.vo.EmallGoodsOrderRelationVo;
import com.wbh.emall.user.common.entity.vo.EmallOrderVo;
import com.wbh.emall.user.common.mapper.EmallGoodsOrderRelationMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WBH
 * @since 2022/2/25
 */
@Service
public class EmallGoodsOrderRelationService extends ServiceImpl<EmallGoodsOrderRelationMapper, EmallGoodsOrderRelation> {
    
    private final EmallGoodsOrderRelationMapper emallGoodsOrderRelationMapper;
    
    public EmallGoodsOrderRelationService(EmallGoodsOrderRelationMapper emallGoodsOrderRelationMapper) {
        this.emallGoodsOrderRelationMapper = emallGoodsOrderRelationMapper;
    }
    
    
    /**
     * 根据订单编号获取订单商品信息
     *
     * @param orderId 订单id
     * @return 订单商品信息
     */
    public List<EmallGoodsOrderRelationVo> getByOrderId(Integer orderId) {
        List<EmallGoodsOrderRelation> emallGoodsOrderRelations = emallGoodsOrderRelationMapper.selectByOrderId(orderId);
        return emallGoodsOrderRelations.stream().map(orderRelation -> {
            EmallGoodsOrderRelationVo orderRelationVo = new EmallGoodsOrderRelationVo();
            BeanUtils.copyProperties(orderRelation, orderRelationVo);
            return orderRelationVo;
        }).collect(Collectors.toList());
    }
    
    
}
