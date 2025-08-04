package com.wangxin.consumer.service.common.impl;

import com.wangxin.common.api.common.salt.Encodes;
import com.wangxin.consumer.service.common.SaltService;

public class DefaultSaltService implements SaltService {
    @Override
    public byte[] decodeHex(String hexSalt) {
        return Encodes.decodeHex(hexSalt);
    }
}
