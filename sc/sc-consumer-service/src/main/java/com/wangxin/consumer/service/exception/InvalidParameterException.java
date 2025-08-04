package com.wangxin.consumer.service.exception;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(String message) {
        super(message);
    }
    public InvalidParameterException(String message, String msg) {
        super(message.concat(msg));
    }
}