package com.wbh.emall.user.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022/2/23
 */
@ApiModel(value = "emall_goods_cover")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "emall_goods_cover")
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldNameConstants
public class EmallGoodsCover {
    @TableId(value = "id")
    @ApiModelProperty(value = "")
    private Integer id;
    
    @TableField(value = "url")
    @ApiModelProperty(value = "")
    private String url;
    
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "")
    private Date gmtCreate;
    
    @TableField(value = "gmt_update", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "")
    private Date gmtUpdate;
    
}