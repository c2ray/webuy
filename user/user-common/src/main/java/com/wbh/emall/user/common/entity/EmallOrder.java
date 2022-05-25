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
@ApiModel(value = "emall_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "emall_order")
@FieldNameConstants
public class EmallOrder {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;
    
    /**
     * 用户id
     */
    @TableField(value = "custom_id")
    @ApiModelProperty(value = "用户id")
    private Integer customId;
    
    /**
     * 总金额
     */
    @TableField(value = "total_amount")
    @ApiModelProperty(value = "总金额")
    private Double totalAmount;
    
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "")
    private Date gmtCreate;
    
    @TableField(value = "gmt_update", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "")
    private Date gmtUpdate;
    
    @TableField(value = "pay_status")
    @ApiModelProperty(value = "支付状态")
    Integer payStatus;
}