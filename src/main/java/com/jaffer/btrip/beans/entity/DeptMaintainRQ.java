package com.jaffer.btrip.beans.entity;

import lombok.Data;

import java.util.List;

@Data
public class DeptMaintainRQ {

    private String corpId;

    private Long deptId;

    private String deptName;

    private String managerIds;

    private Long deptPid;

}
