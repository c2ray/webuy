package com.wbh.emall.common.entity;

/**
 * 常用API返回对象接口
 *
 * @author WBH
 */
public interface IErrorCode {
    /**
     * 返回码
     */
    long getCode();
    
    /**
     * 返回信息
     */
    String getMessage();
}
