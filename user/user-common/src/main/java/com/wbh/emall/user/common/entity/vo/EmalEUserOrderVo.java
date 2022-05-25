package com.wbh.emall.user.common.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author WBH
 * @since 2022/3/23
 */
@Data
public class EmalEUserOrderVo {
    Integer id;
    // 商品名称
    String goodsName;
    // 商品价格
    Double goodsPrice;
    // 商品数量
    Integer goodsNumber;
    // 总金额
    Double totalAmount;
    // 用户昵称
    String userName;
    // 订单创建时间
    Date createTime;
    // 支付状态
    Integer payStatus;
}
