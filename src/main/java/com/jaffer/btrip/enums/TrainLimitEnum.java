package com.jaffer.btrip.enums;

import lombok.Getter;

public enum TrainLimitEnum {

    /**
     * 硬座
     */
    HARD_SEAT("HARD_SEAT","硬座"),

    /**
     * 软座
     */
    SOFT_SEAT("SOFT_SEAT","软座"),

    /**
     * 硬卧
     */
    HARD_SLEEPER("HARD_SLEEPER","硬卧"),

    /**
     * 软卧
     */
    SOFT_SLEEPER("SOFT_SLEEPER","软卧"),

    /**
     *二等座
     */
    SECOND_CLASS("SECOND_CLASS","二等座"),

    /**
     * 一等座
     */
    FIRST_CLASS("FIRST_CLASS","一等座"),

    /**
     * 商务座
     */
    BUSINESS_CLASS("BUSINESS_CLASS","商务座"),

    ;


    /**
     * 名称
     */
    @Getter
    private String name;

    /**
     * 描述
     */
    @Getter
    private String desc;


    TrainLimitEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
