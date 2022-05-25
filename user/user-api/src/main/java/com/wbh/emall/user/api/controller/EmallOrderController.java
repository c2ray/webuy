package com.wbh.emall.user.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wbh.emall.common.entity.Result;
import com.wbh.emall.common.exception.AuthenticationException;
import com.wbh.emall.common.util.EncryptUtil;
import com.wbh.emall.common.util.Objects;
import com.wbh.emall.user.common.entity.EmallGoods;
import com.wbh.emall.user.common.entity.EmallGoodsOrderRelation;
import com.wbh.emall.user.common.entity.EmallOrder;
import com.wbh.emall.user.common.entity.dto.EmallOrderDto;
import com.wbh.emall.user.common.entity.vo.EmallGoodsOrderRelationVo;
import com.wbh.emall.user.common.entity.vo.EmallGoodsVo;
import com.wbh.emall.user.common.entity.vo.EmallOrderVo;
import com.wbh.emall.user.common.entity.vo.OrderGoodsUnionVo;
import com.wbh.emall.user.common.service.EmallGoodsOrderRelationService;
import com.wbh.emall.user.common.service.EmallGoodsService;
import com.wbh.emall.user.common.service.EmallOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 提交订单
 *
 * @author WBH
 * @since 2022/3/20
 */
@RestController
@RequestMapping("/api/order")
public class EmallOrderController {
    
    private final EmallOrderService emallOrderService;
    
    private final EmallGoodsOrderRelationService emallGoodsOrderRelationService;
    private final EmallGoodsService goodsService;
    
    public EmallOrderController(EmallOrderService emallOrderService,
                                EmallGoodsOrderRelationService emallGoodsOrderRelationService, EmallGoodsService goodsService) {
        this.emallOrderService = emallOrderService;
        this.emallGoodsOrderRelationService = emallGoodsOrderRelationService;
        this.goodsService = goodsService;
    }
    
    @ApiOperation("提交订单")
    @PostMapping
    public Result<String> postOrder(@RequestHeader("Authorization") String token,
                                    @RequestBody EmallOrderDto emallOrderDto) throws AuthenticationException, IllegalAccessException {
        Objects.assertFieldsFilled(emallOrderDto,
                EmallOrderDto.Fields.orderRelations,
                EmallOrder.Fields.totalAmount);
        Integer uid = EncryptUtil.decryptId(token);
        // 先记录订单信息 emallOrder,获取订单的id
        Integer orderId = emallOrderService.addOrder(uid, emallOrderDto.getTotalAmount());
        // 再用订单id记录订单商品关系表
        List<EmallGoodsOrderRelation> orderRelations = emallOrderDto.getOrderRelations();
        orderRelations = orderRelations.stream()
                .peek(orderRelation -> orderRelation.setOrderId(orderId))
                .collect(Collectors.toList());
        emallGoodsOrderRelationService.saveBatch(orderRelations);
        return Result.success("提交订单成功!");
    }
    
    
    @ApiOperation("查看订单")
    @GetMapping("/list")
    public Result<List<OrderGoodsUnionVo>> getOrders(@RequestHeader("Authorization") String token) throws AuthenticationException {
        Integer uid = EncryptUtil.decryptId(token);
        List<EmallOrderVo> orders = emallOrderService.getOrderByUid(uid);
        List<OrderGoodsUnionVo> goodsUnionList = orders.stream().map(order -> {
            OrderGoodsUnionVo orderGoodsUnionVo = new OrderGoodsUnionVo();
            Integer orderId = order.getId();
            orderGoodsUnionVo.setOrderId(orderId);
            orderGoodsUnionVo.setTotalAmount(order.getTotalAmount());
            orderGoodsUnionVo.setCreateTime(order.getGmtCreate());
            List<EmallGoodsOrderRelationVo> orderRelations = emallGoodsOrderRelationService.getByOrderId(orderId);
            List<EmallGoodsVo> goodsList = orderRelations.stream().map(orderRelation -> {
                Integer goodsId = orderRelation.getGoodsId();
                EmallGoodsVo emallGoodsVo = goodsService.selectSimpeGoodsById(goodsId);
                Integer num = orderRelation.getGoodsNumber();
                emallGoodsVo.setNum(num);
                //  获取商品详情信息
                return emallGoodsVo;
            }).collect(Collectors.toList());
            orderGoodsUnionVo.setGoodsList(goodsList);
            return orderGoodsUnionVo;
        }).collect(Collectors.toList());
        return Result.success(goodsUnionList);
    }
    
    
}
