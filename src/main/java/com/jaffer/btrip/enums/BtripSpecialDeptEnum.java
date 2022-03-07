package com.jaffer.btrip.enums;

import lombok.Getter;

public enum BtripSpecialDeptEnum {
    /**
     * 根部门
     */
    ROOT_DEPT(1L, "根部门"),
    ;

    BtripSpecialDeptEnum(Long deptId, String desc) {
        this.deptId = deptId;
        this.desc = desc;
    }

    @Getter
    private Long deptId;
    @Getter
    private String desc;
}
