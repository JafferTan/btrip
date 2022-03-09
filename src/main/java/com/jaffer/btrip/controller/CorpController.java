package com.jaffer.btrip.controller;

import com.jaffer.btrip.beans.entity.CorpPO;
import com.jaffer.btrip.service.CorpService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Slf4j
public class CorpController {

    @Autowired
    private CorpService corpService;

    @GetMapping("/corpInfo")
    public String helloWorld() {
        return "corpInfo";
    }

    @PostMapping("/registerCorpJson")
    @ResponseBody
    public BtripResult<Boolean> registerCorp(@RequestParam("corpName") String corpName, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("userName") String userName) {
        if (StringUtils.isEmpty(corpName) || StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(userName)) {
            return BtripResultUtils.returnFailMsg("非法入参");
        }

        try {
            BtripResult<Boolean> result = corpService.registerCorp(corpName, phoneNumber, userName);
            if (result == null || BooleanUtils.isFalse(result.getSuccess())) {
                return BtripResultUtils.returnFailMsg("注册企业失败，失败原因");
            }
            return result;
        } catch (Exception e) {
            log.error("registerCorp error, corpName:{}, phoneNumber:{}, userName:{}", corpName, phoneNumber, userName,e);
            return BtripResultUtils.returnFailMsg("注册企业异常,异常原因 :" + e.getMessage());
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
