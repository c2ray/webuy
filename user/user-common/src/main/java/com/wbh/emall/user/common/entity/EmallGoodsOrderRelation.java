package com.wbh.emall.user.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.Date;

/**
 * @author WBH
 * @since 2022/2/25
 */

/**
 * 商品订单关联表
 */
@ApiModel(value = "商品订单关联表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "emall_goods_order_relation")
@FieldNameConstants
public class EmallGoodsOrderRelation {
    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "记录id")
    private Integer id;
    
    /**
     * 订单id
     */
    @TableField(value = "order_id")
    @ApiModelProperty(value = "订单id")
    private Integer orderId;
    
    /**
     * 商品id
     */
    @TableField(value = "goods_id")
    @ApiModelProperty(value = "商品id")
    private Integer goodsId;
    
    /**
     * 商品数量
     */
    @TableField(value = "goods_number")
    @ApiModelProperty(value = "商品数量")
    private Integer goodsNumber;
    
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "")
    private Date gmtCreate;
    
    @TableField(value = "gmt_update", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "")
    private Date gmtUpdate;
}