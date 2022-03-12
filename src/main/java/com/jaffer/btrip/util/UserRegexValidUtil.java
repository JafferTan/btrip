package com.jaffer.btrip.util;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;

public class UserRegexValidUtil {

    /**
     * 校验手机号的正则表达式
     * @param phoneNumber
     */
    public static void validPhoneNumber(String phoneNumber) {
        if(StringUtils.isEmpty(phoneNumber)){
            return;
        }
        Preconditions.checkArgument(phoneNumber.matches( "^1\\d{10}$"),
                "手机号为11位数字。错误的手机号码为:%s",phoneNumber);
    }


    /**
     * 校验验证码的正则表达式
     * @param authCode
     */
    public static void validAuthCode(String authCode) {
        if(StringUtils.isEmpty(authCode)){
            return;
        }
        Preconditions.checkArgument(authCode.matches( "^\\d{4}$"),
                "验证码为6位数字。错误的验证码为:%s",authCode);
    }
}
