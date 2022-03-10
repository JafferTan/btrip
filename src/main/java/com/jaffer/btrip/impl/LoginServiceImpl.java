package com.jaffer.btrip.impl;

import com.jaffer.btrip.beans.entity.UserCorpsVO;
import com.jaffer.btrip.beans.entity.LoginInfo;
import com.jaffer.btrip.manager.CorpManager;
import com.jaffer.btrip.manager.UseManager;
import com.jaffer.btrip.service.LoginService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import com.jaffer.btrip.util.RedisUtils;
import com.jaffer.btrip.util.UserRegexValidUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UseManager userManager;

    @Autowired
    private CorpManager corpManager;

    private static final String GET_AUTH_CODE_BY_PHONE_NUMBER = "GET_AUTH_CODE_BY_PHONE_NUMBER_%s";
    @Override
    public BtripResult<LoginInfo> loginByAuthCode(String phoneNumber, String authCode) {
        try {
            UserRegexValidUtil.validPhoneNumber(phoneNumber);

            Jedis jedis = RedisUtils.getJedis();
            String key = String.format(GET_AUTH_CODE_BY_PHONE_NUMBER, phoneNumber);
            boolean exists = jedis.exists(key);
            if (BooleanUtils.isFalse(exists)) {
                return BtripResultUtils.returnFailMsg("请先获取验证码");
            }

            String authCodeFromRedis = jedis.get(key);
            if (!StringUtils.equals(authCode, authCodeFromRedis)) {
                return BtripResultUtils.returnFailMsg("验证码不正确");
            }

            List<UserCorpsVO> userCorpListByPhoneNumber = corpManager.findUserCorpListByPhoneNumber(phoneNumber);
            if (CollectionUtils.isEmpty(userCorpListByPhoneNumber)) {
                return BtripResultUtils.returnFailMsg("用户不存在");
            }
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setCorpVOList(userCorpListByPhoneNumber);
            return BtripResultUtils.returnSuccess(loginInfo);
        } catch (Exception e) {
            log.error("loginByAuthCode fail, phoneNumber:{}", phoneNumber,e);
            return BtripResultUtils.returnFailMsg("获取验证码失败，失败原因:" + e.getMessage());
        }

    }

    @Override
    public BtripResult<Boolean> getAuthCode(String phoneNumber) {
        try {
            UserRegexValidUtil.validPhoneNumber(phoneNumber);

            Jedis jedis = RedisUtils.getJedis();
            String key = String.format(GET_AUTH_CODE_BY_PHONE_NUMBER, phoneNumber);
            boolean exists = jedis.exists(key);
            if (BooleanUtils.isFalse(exists)) {
                String authCode = StringUtils.leftPad(new Random().nextInt(10000) + "", 6, "0");
                jedis.setex(key,600,authCode);
            }
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("getAuthCode fail, phoneNumber:{}", phoneNumber,e);
            return BtripResultUtils.returnFailMsg("获取验证码失败，失败原因:" + e.getMessage());
        }
    }
}
