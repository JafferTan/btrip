package com.jaffer.btrip.beans.entity;

import lombok.Data;

/**
 * 部门的省略对象
 * 防止返回给前端数据过大
 */
@Data
public class DeptLimitVO {

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 部门主管设置
     */
    private String managerIds;

    /**
     * 是否有子部门
     */
    private Boolean hasSubDept;
}
