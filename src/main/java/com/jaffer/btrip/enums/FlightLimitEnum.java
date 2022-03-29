package com.jaffer.btrip.enums;

import lombok.Getter;

public enum FlightLimitEnum {

    /**
     * 每新增一个必须不能占用对应的比特位，否则会出现冲突
     */


    /**
     * 经济舱
     */
    ECONOMY_CLASS("ECONOMY_CLASS", "经济舱"),

    /**
     * 商务舱
     */
    BUSINESS_CLASS("BUSINESS_CLASS","商务舱"),

    /**
     * 头等舱
     */
    FIRST_CLASS("FIRST_CLASS","头等舱"),


    ;

    @Getter
    private String name;

    @Getter
    private String desc;

    FlightLimitEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }


}
