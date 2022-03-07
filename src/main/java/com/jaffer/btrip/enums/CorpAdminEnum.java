package com.jaffer.btrip.enums;

import lombok.Getter;

public enum CorpAdminEnum {


    /**
     * 管理员
     */
    ADMIN("ADMIN","管理员"),

    /**
     * 超级管理员
     */
    SUPER_ADMIN("SUPER_ADMIN","超级管理员"),

    ;


    CorpAdminEnum(String adminType, String desc) {
        this.adminType = adminType;
        this.desc = desc;
    }

    @Getter
    private String adminType;

    @Getter
    private String desc;
}
