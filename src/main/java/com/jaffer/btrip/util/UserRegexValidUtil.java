package com.jaffer.btrip.util;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;

public class UserRegexValidUtil {

    public static void validPhoneNumber(String phoneNumber) {
        if(StringUtils.isEmpty(phoneNumber)){
            return;
        }
        Preconditions.checkArgument(phoneNumber.matches( "^1\\d{10}$"),
                "手机号为11位数字。错误的手机号码为:%s",phoneNumber);
    }

    public static void validAuthCode(String authCode) {
        if(StringUtils.isEmpty(authCode)){
            return;
        }
        Preconditions.checkArgument(authCode.matches( "^\\d{6}$"),
                "验证码为6位数字。错误的验证码为:%s",authCode);
    }
}
