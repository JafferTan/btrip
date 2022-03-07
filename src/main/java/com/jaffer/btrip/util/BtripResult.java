package com.jaffer.btrip.util;

import lombok.Data;

@Data
public class BtripResult<T>{

    private Boolean success;

    private String errorMsg;

    private Integer resultCode;

    private T module;
}
