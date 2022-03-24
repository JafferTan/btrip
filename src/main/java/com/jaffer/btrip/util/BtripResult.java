package com.jaffer.btrip.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class BtripResult<T> implements Serializable {

    /**
     * 调用结果
     */
    private Boolean success = false;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 错误码
     */
    private Integer resultCode;

    /**
     * 返回的对象
     */
    private T module;
}
