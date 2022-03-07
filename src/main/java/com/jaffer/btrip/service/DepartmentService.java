package com.jaffer.btrip.service;

import com.jaffer.btrip.util.BtripResult;

/**
 * 部门接口层
 */
public interface DepartmentService {

    /**
     * 删除部门
     * @param corpId 企业ID
     * @param deptId 部门ID
     * @return
     */
    BtripResult<Boolean> deleteDept(String corpId, Long deptId);

    /**
     * 创建或者删除部门
     * @param corpId 企业ID
     * @param deptId 部门ID
     * @return
     */
    BtripResult<Boolean> createOrUpdateDept(String corpId, Long deptId);
}
