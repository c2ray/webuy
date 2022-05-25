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
 * 商品图片表
 */
@ApiModel(value = "商品图片表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "emall_goods_pic")
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldNameConstants
public class EmallGoodsPic {
    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;
    
    @TableField(value = "goods_id")
    @ApiModelProperty(value = "")
    private Integer goodsId;
    
    @TableField(value = "url")
    @ApiModelProperty(value = "")
    private String url;
    
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "")
    private Date gmtCreate;
    
    @TableField(value = "gmt_update", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "")
    private Date gmtUpdate;
    
    @TableField(value = "legal")
    @ApiModelProperty(value = "")
    private Integer legal;
    
}