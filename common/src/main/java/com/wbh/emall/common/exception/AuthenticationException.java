package com.wbh.emall.common.exception;

/**
 * @author WBH
 * @since 2022/2/16
 */
public class AuthenticationException extends Exception {
    
    private static final long serialVersionUID = -8454827098298800L;
    
    /**
     * 创建认证异常
     *
     * @param message 异常信息
     */
    public AuthenticationException(String message) {
        super(message);
    }
    
    /**
     * 创建认证异常
     *
     * @param message 异常信息
     * @param cause   异常原因
     */
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
