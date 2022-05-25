package com.wbh.emall.common.entity;

import lombok.ToString;

/**
 * @author WBH
 * @since 2022/2/16
 */
@ToString(callSuper = true)
public class LoginResult<T> extends Result<T> {
    
    private final String token;
    
    protected LoginResult(long code, String msg, T data, String token) {
        super(code, msg, data);
        this.token = token;
    }
    
    /**
     * 成功返回结果
     *
     * @param data       获取的数据
     * @param message    提示信息
     * @param token 认证信息
     */
    public static <T> LoginResult<T> success(T data, String message, String token) {
        return new LoginResult<>(ResultCode.SUCCESS.getCode(), message, data, token);
    }
    
    /**
     * 成功返回结果
     *
     * @param data       获取的数据
     * @param token 认证信息
     */
    public static <T> LoginResult<T> success(T data, String token) {
        return new LoginResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, token);
    }
    
    public String gettoken() {
        return token;
    }
    
}
