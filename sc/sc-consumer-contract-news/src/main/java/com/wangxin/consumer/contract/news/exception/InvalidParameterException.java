package com.wangxin.consumer.contract.news.exception;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(String message) {
        super(message);
    }
    public InvalidParameterException(String message, String msg) {
        super(msg + ":" + message);;
    }
}