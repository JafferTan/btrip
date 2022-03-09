package com.jaffer.btrip.impl;

import com.jaffer.btrip.beans.entity.DeptMaintainRQ;
import com.jaffer.btrip.beans.entity.DeptPO;
import com.jaffer.btrip.manager.DeptManager;
import com.jaffer.btrip.service.DeptService;
import com.jaffer.btrip.util.BtripResult;
import com.jaffer.btrip.util.BtripResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptManager deptManager;

    @Override
    public BtripResult<Boolean> createOrEditDept(DeptMaintainRQ rq) {
        try {
            if (Objects.isNull(rq.getDeptId())) {
                deptManager.createDept(rq);
            } else {
                deptManager.editDept(rq);
            }
            return BtripResultUtils.returnSuccess(true);
        } catch (Exception e) {
            log.error("createOrEditDept fail, rq:{}", rq, e);
            return BtripResultUtils.returnFailMsg("维护部门失败,失败原因:" + e.getMessage());
        }
    }

    @Override
    public BtripResult<DeptPO> getDeptDetailByDeptId(String corpId, Long deptId) {
        try {
            DeptPO dept = deptManager.getDeptByDeptId(corpId, deptId);
            if (Objects.isNull(dept)) {
                return BtripResultUtils.returnFailMsg("查询的部门不存在");
            }
            return BtripResultUtils.returnSuccess(dept);
        } catch (Exception e) {
            log.error("createOrEditDept fail, corpId:{}, deptId:{}", corpId, deptId, e);
            return BtripResultUtils.returnFailMsg("查询的部门失败,失败原因:" + e.getMessage());
        }
    }
}
