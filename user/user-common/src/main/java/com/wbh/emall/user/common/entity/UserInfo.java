package com.wbh.emall.user.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

/**
 * @author WBH
 * @since 2022/2/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldNameConstants
public class UserInfo {
    /**
     * 用户登录的code
     */
    private String code;
    
    /**
     * 头像url
     */
    private String avatarUrl;
    
    /**
     * 昵称
     */
    private String nickName;
}
