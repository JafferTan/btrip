package com.jaffer.btrip.enums;

import lombok.Getter;

public enum VehicleTypeEnum {

    FLIGHT("flight","飞机"),
    TRAIN("train", "火车"),
    HOTEL("hotel","酒店");

    @Getter
    public final String type;

    @Getter
    public final String desc;

    VehicleTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
