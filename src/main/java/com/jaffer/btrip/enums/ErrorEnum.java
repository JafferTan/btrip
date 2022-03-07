package com.jaffer.btrip.enums;

import lombok.Getter;

public enum ErrorEnum {

    /**
     * 系统错误
     */
    SYSTEM_ERROR(-1,"系统错误"),


    /**
     * 成功
     */
    SUCCESS(0,"成功"),

    ;

    ErrorEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 错误码
     */
    @Getter
    private Integer errorCode;

    /**
     * 错误信息
     */
    @Getter
    private String errorMsg;
}
