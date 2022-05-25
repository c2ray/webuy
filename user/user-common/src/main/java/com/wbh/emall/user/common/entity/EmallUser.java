package com.wbh.emall.user.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.Date;


/**
 * 用户表
 *
 * @author WBH
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldNameConstants
public class EmallUser {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;
    
    /**
     * 用户openid
     */
    @TableField(value = "openid")
    @ApiModelProperty(value = "用户openid")
    private String openid;
    
    /**
     * 用户unionid
     */
    @TableField(value = "unionid")
    @ApiModelProperty(value = "用户unionid")
    private String unionid;
    
    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    
    @TableField(value = "gmt_update", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "")
    private Date gmtUpdate;

    
    /**
     * 用户头像
     */
    @TableField(value = "avatar_url")
    @ApiModelProperty(value = "用户头像")
    private String avatarUrl;
    
    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    
    /**
     * 用户账号
     */
    @TableField(value = "username")
    @ApiModelProperty(value = "用户账号")
    private String username;
    
    /**
     * 用户密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value = "用户密码")
    private String password;
    
    /**
     * 手机号
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "手机号")
    private String phone;
    
    @TableField(value = "true_name")
    @ApiModelProperty(value = "真实姓名")
    private String trueName;
    
    @TableField(value = "id_card")
    @ApiModelProperty(value = "身份证号码")
    private String idCard;
}