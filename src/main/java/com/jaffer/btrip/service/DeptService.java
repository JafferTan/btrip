package com.jaffer.btrip.service;

import com.jaffer.btrip.beans.entity.DeptMaintainRQ;
import com.jaffer.btrip.beans.entity.DeptPO;
import com.jaffer.btrip.util.BtripResult;

public interface DeptService {

    BtripResult<Boolean> createOrEditDept(DeptMaintainRQ rq);

    BtripResult<DeptPO> getDeptDetailByDeptId(String corpId, Long deptId);

    BtripResult<Boolean> deleteDept(String corpId, Long deptId);

}
