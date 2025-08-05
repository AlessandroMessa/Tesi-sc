package com.wangxin.consumer.contract.auth;

public interface SaltService {
    byte[] decodeHex(String hexSalt);
}
