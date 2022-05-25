package com.wbh.emall.common.exception;


public class EmallException extends Exception {
    private static final long serialVersionUID = -8454827098298800L;
    
    /**
     * 创建认证异常
     *
     * @param message 异常信息
     */
    public EmallException(String message) {
        super(message);
    }
    
    /**
     * 创建认证异常
     *
     * @param message 异常信息
     * @param cause   异常原因
     */
    public EmallException(String message, Throwable cause) {
        super(message, cause);
    }
}
