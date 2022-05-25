package com.wbh.emall.user.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbh.emall.user.common.entity.EmallOrder;
import com.wbh.emall.user.common.entity.vo.EmalEUserOrderVo;
import com.wbh.emall.user.common.entity.vo.EmallOrderVo;
import com.wbh.emall.user.common.mapper.EmallOrderMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WBH
 * @since 2022/2/25
 */
@Service
public class EmallOrderService extends ServiceImpl<EmallOrderMapper, EmallOrder> {
    
    private final EmallOrderMapper emallOrderMapper;
    
    public EmallOrderService(EmallOrderMapper emallOrderMapper) {
        this.emallOrderMapper = emallOrderMapper;
    }
    
    
    /**
     * 添加订单信息
     *
     * @param customId    用户id
     * @param totalAmount 订单总金额
     * @return 订单的id
     */
    public Integer addOrder(Integer customId, Double totalAmount) {
        EmallOrder emallOrder = new EmallOrder(null, customId, totalAmount, null, null,null);
        emallOrderMapper.insert(emallOrder);
        return emallOrder.getId();
    }
    
    /**
     * 根据用户id获取用户订单
     *
     * @param uid 用户id
     * @return 用户订单信息
     */
    public List<EmallOrderVo> getOrderByUid(Integer uid) {
        List<EmallOrder> emallOrders = emallOrderMapper.selectOrderByUserId(uid);
        return emallOrders.stream().map(emallOrder -> {
            EmallOrderVo emallOrderVo = new EmallOrderVo();
            BeanUtils.copyProperties(emallOrder, emallOrderVo);
            return emallOrderVo;
        }).collect(Collectors.toList());
    }
    
    
    /**
     * 根据商家id获取商家的收到的订单
     *
     * @param uid 商家的uid
     * @return 商家的订单信息
     */
    public IPage<EmalEUserOrderVo> getOrderByEUserid(Integer uid, Integer current, Integer size) {
        IPage<EmalEUserOrderVo> emalEUserOrderVoIPage = emallOrderMapper.selectOrderByPage(new PageDTO<>(current, size), uid);
        emalEUserOrderVoIPage.setRecords(emalEUserOrderVoIPage.getRecords().stream()
                .peek(emalEUserOrderVo -> emalEUserOrderVo.setTotalAmount(emalEUserOrderVo.getGoodsPrice() * emalEUserOrderVo.getGoodsNumber()))
                .collect(Collectors.toList()));
        return emalEUserOrderVoIPage;
    }
    
}
