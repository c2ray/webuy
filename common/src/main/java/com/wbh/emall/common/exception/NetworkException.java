package com.wbh.emall.common.exception;

/**
 * @author WBH
 * @since 2022/2/23
 */
public class NetworkException extends Exception {
    public NetworkException() {
        super();
    }
    
    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
