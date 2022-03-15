package com.jaffer.btrip.beans.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SubDeptVO implements Serializable {

    /**
     * 当前部门的id
     */
    private Long deptId;

    /**
     * 子部门信息
     */
    private List<DeptLimitVO> subDeptInfo;
}
