package com.jaffer.btrip.controller;

import com.jaffer.btrip.beans.entity.DeptMaintainRQ;
import com.jaffer.btrip.beans.entity.DeptPO;
import com.jaffer.btrip.beans.entity.LoginInfo;
import com.jaffer.btrip.service.DeptService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import com.jaffer.btrip.util.BtripSessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/deptInfo")
    public String helloWorld() {
        return "/deptInfo";
    }


    @PostMapping("/createOrEditDeptJson")
    @ResponseBody
    public BtripResult<Boolean> createOrEditDept(DeptMaintainRQ deptMaintainRQ) {
        try {
            LoginInfo loginInfo = BtripSessionUtils.getLoginInfo();
            deptMaintainRQ.setCorpId(loginInfo.getCorpId());
            this.checkDeptMaitainRQ(deptMaintainRQ);

            BtripResult<Boolean> result = deptService.createOrEditDept(deptMaintainRQ);
            if (result == null || BooleanUtils.isFalse(result.getSuccess())) {
                return BtripResultUtils.returnFailMsg("维护部门信息失败，失败原因" + result.getErrorMsg());
            }
            return result;

        } catch (Exception e) {
            log.error("createOrEditDept error, deptMaintainRQ:{}", deptMaintainRQ, e);
            return BtripResultUtils.returnFailMsg("维护部门信息异常,异常原因 :" + e.getMessage());
        }
    }

    /**
     * 校验部门rq
     * @param deptMaintainRQ
     */
    private void checkDeptMaitainRQ(DeptMaintainRQ deptMaintainRQ) {
        if (StringUtils.isEmpty(deptMaintainRQ.getDeptName())) {
            throw new IllegalArgumentException("部门名称缺失");
        }
        if (Objects.isNull(deptMaintainRQ.getDeptPid())) {
            throw new IllegalArgumentException("父部门信息缺失");
        }
        if (Objects.isNull(deptMaintainRQ.getCorpId())) {
            throw new IllegalArgumentException("企业id缺失");
        }
    }

    @PostMapping("/getDeptDetailJson")
    @ResponseBody
    public BtripResult<DeptPO> getDeptDetail(Long deptId) {
        try {
            LoginInfo loginInfo = BtripSessionUtils.getLoginInfo();
            String corpId = loginInfo.getCorpId();
            BtripResult<DeptPO> result = deptService.getDeptDetailByDeptId(corpId, deptId);
            if (result == null || BooleanUtils.isFalse(result.getSuccess())) {
                return BtripResultUtils.returnFailMsg("查询部门信息失败，失败原因" + result.getErrorMsg());
            }
            return result;
        } catch (Exception e) {
            log.error("getDeptDetail error, deptId:{}", deptId, e);
            return BtripResultUtils.returnFailMsg("查询部门信息异常,异常原因 :" + e.getMessage());
        }
    }


    @PostMapping("/deleteDeptJson")
    @ResponseBody
    public BtripResult<DeptPO> deleteDept(Long deptId) {
        try {
            LoginInfo loginInfo = BtripSessionUtils.getLoginInfo();
            String corpId = loginInfo.getCorpId();
            BtripResult<DeptPO> result = deptService.getDeptDetailByDeptId(corpId, deptId);
            if (result == null || BooleanUtils.isFalse(result.getSuccess())) {
                return BtripResultUtils.returnFailMsg("查询部门信息失败，失败原因" + result.getErrorMsg());
            }
            return result;
        } catch (Exception e) {
            log.error("getDeptDetail error, deptId:{}", deptId, e);
            return BtripResultUtils.returnFailMsg("查询部门信息异常,异常原因 :" + e.getMessage());
        }
    }

}
