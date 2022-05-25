package com.wbh.emall.user.common.entity.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author WBH
 * @since 2022/3/21
 */
@Data
public class OrderGoodsUnionVo {
    
    Integer orderId;
    
    List<EmallGoodsVo> goodsList;
    
    Double totalAmount;
    
    Date createTime;
}
