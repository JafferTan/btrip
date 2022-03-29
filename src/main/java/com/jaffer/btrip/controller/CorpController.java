package com.jaffer.btrip.controller;

import com.jaffer.btrip.beans.entity.CorpPO;
import com.jaffer.btrip.beans.entity.LoginInfo;
import com.jaffer.btrip.beans.entity.UserPO;
import com.jaffer.btrip.service.CorpService;
import com.jaffer.btrip.service.UserService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import com.jaffer.btrip.util.BtripSessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
@Slf4j
public class CorpController {

    @Autowired
    private CorpService corpService;

    @Autowired
    private UserService userService;

    @PostMapping("/registerCorpJson")
    public ModelAndView registerCorp(@RequestParam("corpName") String corpName, @RequestParam("userName") String userName) {

        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> model = modelAndView.getModel();
        LoginInfo loginInfo = BtripSessionUtils.getLoginInfo();
        String phoneNumber = loginInfo.getPhoneNumber();

        if (StringUtils.isEmpty(corpName) || StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(userName)) {
            model.put("failReason","非法入参");
            modelAndView.setViewName("login");
            return modelAndView;
        }

        try {
            BtripResult<String> result = corpService.registerCorp(corpName, phoneNumber, userName);
            if (result == null || BooleanUtils.isFalse(result.getSuccess())) {
                model.put("failReason","创建企业失败");
                modelAndView.setViewName("login");
                return modelAndView;
            }
            String corpId = result.getModule();
            BtripResult<CorpPO> corpDetailByCorpId = corpService.getCorpDetailByCorpId(corpId);
            if (corpDetailByCorpId == null || BooleanUtils.isFalse(corpDetailByCorpId.getSuccess())) {
                model.put("failReason","获取企业信息失败");
                modelAndView.setViewName("login");
                return modelAndView;
            }
            CorpPO corp = corpDetailByCorpId.getModule();
            loginInfo.setCorpId(corp.getCorpId());
            loginInfo.setCorpName(corp.getCorpName());

            BtripResult<UserPO> userDetailByPhoneNumber = userService.getUserDetailByPhoneNumber(corpId, loginInfo.getPhoneNumber());
            if (userDetailByPhoneNumber == null || BooleanUtils.isFalse(userDetailByPhoneNumber.getSuccess())) {
                model.put("failReason","获取超级管理信息失败");
                modelAndView.setViewName("login");
                return modelAndView;
            }

            UserPO user = userDetailByPhoneNumber.getModule();
            loginInfo.setUserId(user.getUserId());
            loginInfo.setUserName(user.getUserName());
            model.put("loginInfo", loginInfo);
            modelAndView.setViewName("index");
            return modelAndView;
        } catch (Exception e) {
            log.error("registerCorp error, corpName:{}, phoneNumber:{}, userName:{}", corpName, phoneNumber, userName,e);
            model.put("failReason","创建企业失败");
            modelAndView.setViewName("login");
            return modelAndView;
        }

    }

    @PostMapping("/getCorpDetailByCorpIdJson")
    @ResponseBody
    public BtripResult<CorpPO> getCorpDetailByCorpId(@RequestParam("corpId") String corpId) {

        try {
            BtripResult<CorpPO> result = corpService.getCorpDetailByCorpId(corpId);
            return result;
        } catch (Exception e) {
            log.error("getCorpDetailByCorpId error, corpId:{}", corpId,e);
            return BtripResultUtils.returnFailMsg("查询企业异常,异常原因 :" + e.getMessage());
        }
    }

    @PostMapping("/deleteCorpByCorpIdJson")
    @ResponseBody
    public BtripResult<Boolean> deleteCorpByCorpId(@RequestParam("corpId") String corpId) {

        try {
            BtripResult<Boolean> result = corpService.deleteCorpByCorpId(corpId);
            return result;
        } catch (Exception e) {
            log.error("getCorpDetailByCorpId error, corpId:{}", corpId,e);
            return BtripResultUtils.returnFailMsg("查询企业异常,异常原因 :" + e.getMessage());
        }
    }


}
