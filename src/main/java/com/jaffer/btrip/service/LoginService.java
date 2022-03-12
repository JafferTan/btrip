package com.jaffer.btrip.service;

import com.jaffer.btrip.beans.entity.LoginInfo;
import com.jaffer.btrip.util.BtripResult;

public interface LoginService {

    /**
     * 用验证码登陆
     * @param phoneNumber
     * @param authCode
     * @return
     */
    BtripResult<LoginInfo> loginByAuthCode(String phoneNumber, String authCode);


    /**
     * 生成验证码
     */
    BtripResult<Boolean> getAuthCode(String phoneNumber);
}
