package com.wbh.emall.user.common.entity.vo;

import com.wbh.emall.user.common.entity.EmallUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

/**
 * @author WBH
 * @since 2022/2/27
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@FieldNameConstants
public class EmallUserVo extends EmallUser {
    
    private String token;
}
