package com.jaffer.btrip.beans.entity;

import lombok.Data;

@Data
public class DeptMaintainRQ {

    private String corpId;

    private Long deptId;

    private String deptName;

    private String managerIds;

    private Long deptPid;

}
