package com.jaffer.btrip.service;

import com.jaffer.btrip.beans.entity.DeptMaintainRQ;
import com.jaffer.btrip.beans.entity.DeptPO;
import com.jaffer.btrip.beans.entity.SubDeptVO;
import com.jaffer.btrip.util.BtripResult;

import java.util.Set;

public interface DeptService {

    /**
     * 创建或者编辑部门
     * @param rq
     * @return
     */
    BtripResult<Boolean> createOrEditDept(DeptMaintainRQ rq);

    /**
     * 获取部门详情
     * @param corpId
     * @param deptId
     * @return
     */
    BtripResult<DeptPO> getDeptDetailByDeptId(String corpId, Long deptId);

    /**
     * 删除部门
     * @param corpId
     * @param deptId
     * @return
     */
    BtripResult<Boolean> deleteDept(String corpId, Long deptId);


    /**
     * 获取一个部门的主管ids
     * @param corpId
     * @param deptId
     * @return
     */
    BtripResult<Set<String>> getDeptManagerIds(String corpId, Long deptId);

    /**
     * 获取一个部门的子部门数据
     * @param corpId
     * @param deptId
     * @return
     */
    BtripResult<SubDeptVO> getSubDeptDetail(String corpId, Long deptId);


}
