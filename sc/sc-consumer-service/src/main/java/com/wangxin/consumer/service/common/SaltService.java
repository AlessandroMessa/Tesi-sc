package com.wangxin.consumer.service.common;

public interface SaltService {
    byte[] decodeHex(String hexSalt);
}
