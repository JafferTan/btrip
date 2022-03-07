package com.jaffer.btrip.exception;

import lombok.Data;

@Data
public class BizException extends RuntimeException{
    private int code;

    public BizException(String message) {
        super(message);
    }
}
