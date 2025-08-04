package com.wangxin.consumer.jsp.adapter.feign;

import com.wangxin.consumer.api.common.VersionProvider;
import com.wangxin.common.api.common.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class DateUtilVersionProvider implements VersionProvider {

    @Override
    public String generateVersion() {
        // qui tieni la dipendenza su DateUtil solo nell’adapter
        return DateUtil.dateToString(
            Calendar.getInstance().getTime(),
            DateUtil.fm_yyyyMMddHHmmssSSS
        );
    }
}