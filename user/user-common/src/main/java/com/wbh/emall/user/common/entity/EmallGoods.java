package com.wbh.emall.user.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.Date;

/**
 * @author WBH
 * @since 2022/2/20
 */

/**
 * 商品表
 */
@ApiModel(value = "商品表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "emall_goods")
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldNameConstants
public class EmallGoods {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;
    
    /**
     * 商品名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "商品名称")
    private String name;
    
    /**
     * 商品描述
     */
    @TableField(value = "`DESC`")
    @ApiModelProperty(value = "商品描述")
    private String desc;
    
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "")
    private Date gmtCreate;
    
    @TableField(value = "gmt_update", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "")
    private Date gmtUpdate;
    /**
     * 价格
     */
    @TableField(value = "price")
    @ApiModelProperty(value = "")
    private Double price;
    
    /**
     * 原价
     */
    @TableField(value = "original_price")
    @ApiModelProperty(value = "原价")
    private Double originalPrice;
    
    /**
     * 商品类别id
     */
    @TableField(value = "type_id")
    @ApiModelProperty(value = "商品类别id")
    private Integer typeId;
    
    /**
     * 用户id
     */
    @TableField(value = "u_id")
    @ApiModelProperty(value = "用户id")
    private Integer uId;
    
    /**
     * 封面id
     */
    @TableField(value = "cover_id")
    @ApiModelProperty(value = "封面id")
    private Integer coverId;
    
    /**
     * 计量单位
     */
    @TableField(value = "unit_id")
    @ApiModelProperty(value = "计量单位")
    private Integer unitId;
}