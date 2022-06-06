package com.jaffer.btrip.enums;

import lombok.Getter;

public enum EvectionFormOwnType {

    CREATOR("creator", "创建者"),

    APPROVER("approver", "审批人"),

    ADMIN("admin", "管理员");
    @Getter
    public final String type;

    @Getter
    public final String desc;

    EvectionFormOwnType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
