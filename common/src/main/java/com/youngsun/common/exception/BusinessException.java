package com.youngsun.common.exception;

/**
 * Created by 国平 on 2016/11/27.
 */
public class BusinessException extends Exception {
    private Integer status;
    private String message;
    
    public BusinessException() {
    }
    
    public BusinessException(Integer status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
