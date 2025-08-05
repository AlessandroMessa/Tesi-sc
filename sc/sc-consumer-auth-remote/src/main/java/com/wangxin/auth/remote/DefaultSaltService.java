package com.wangxin.auth.remote;

import com.wangxin.common.api.common.salt.Encodes;
import com.wangxin.consumer.contract.auth.SaltService;


public class DefaultSaltService implements SaltService {
    @Override
    public byte[] decodeHex(String hexSalt) {
        return Encodes.decodeHex(hexSalt);
    }
}
