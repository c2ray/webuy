package com.wbh.emall.common.entity;

import lombok.ToString;

/**
 * 通用返回对象
 *
 * @author WBH
 */
@ToString
public class Result<T> {
    /**
     * 状态码
     */
    private long code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据封装
     */
    private T data;
    
    protected Result() {
    }
    
    protected Result(long code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }
    
    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(ResultCode.SUCCESS.getCode(), message, data);
    }
    
    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> Result<T> failed(IErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getMessage(), null);
    }
    
    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static <T> Result<T> failed(IErrorCode errorCode, String message) {
        return new Result<>(errorCode.getCode(), message, null);
    }
    
    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> Result<T> failed(String message) {
        return new Result<>(ResultCode.FAILED.getCode(), message, null);
    }
    
    /**
     * 失败返回结果
     */
    public static <T> Result<T> failed() {
        return failed(ResultCode.FAILED);
    }
    
    /**
     * 参数验证失败返回结果
     */
    public static <T> Result<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }
    
    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> Result<T> validateFailed(String message) {
        return new Result<>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }
    
    /**
     * 未登录返回结果
     */
    public static <T> Result<T> unauthorized(T data) {
        return new Result<>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }
    
    /**
     * 未授权返回结果
     */
    public static <T> Result<T> forbidden(T data) {
        return new Result<>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }
    
    public long getCode() {
        return code;
    }
    
    public void setCode(long code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
}
