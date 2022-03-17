package com.jaffer.btrip.enums;

import lombok.Getter;

public enum RowStatusEnum {

    /**
     * 正常
     */
    NORMAL("NORMAL","正常"),

    /**
     * 删除
     */
    DELETE("DELETE","删除"),

    ;


    RowStatusEnum(String status, String errorMsg) {
        this.status = status;
        this.errorMsg = errorMsg;
    }

    /**
     * 错误码
     */
    @Getter
    private String status;

    /**
     * 错误信息
     */
    @Getter
    private String errorMsg;
}
