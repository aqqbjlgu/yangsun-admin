package com.youngsun.common.exception;

/**
 * Created by 国平 on 2016/11/27.
 */
public class BudinessRuntimeException extends RuntimeException {
    private Integer status;
    private String  message;
    
    public BudinessRuntimeException(String message, Integer status) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
