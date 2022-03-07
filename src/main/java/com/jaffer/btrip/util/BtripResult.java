package com.jaffer.btrip.util;

import lombok.Data;

@Data
public class BtripResult<T>{
    Boolean success;

    String errorMsg;

    Integer resultCode;

    T module;
}
