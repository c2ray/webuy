package com.wbh.emall.user.common.entity.vo;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

/**
 * @author WBH
 * @since 2022/3/1
 */
@Data
@FieldNameConstants
public class PasswordVo {
    private String oldPassword;
    private String newPassword;
}
