package com.qifenqian.bms.v2.common.mvc.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * @author LiBin
 * @Description: 日期转换
 * @date 2020/4/3 15:25
 */
public class DateParamsConverter implements Converter<String, Date> {

    private static String[] pattern =
            new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S",
                    "yyyy.MM.dd", "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss.S",
                    "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss.S",
                    "yyyy-MM-dd'T'HH:mm:ss.SSS Z"
            };

    @Override
    public Date convert(String value) {
        if (value.contains("Z")) {
            value = value.replace("Z", " UTC");//UTC是本地时间
        }
        Date targetDate = null;
        if (StringUtils.isNotEmpty(value)) {
            try {
                targetDate = DateUtils.parseDate(value, DateParamsConverter.pattern);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return targetDate;
    }

}
