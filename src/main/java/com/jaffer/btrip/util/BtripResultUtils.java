package com.jaffer.btrip.util;

public class BtripResultUtil{
    public static <T> BtripResult<T> returnFailMsg(String errorMsg) {
        BtripResult<T> res = new BtripResult<T>();
        res.setSuccess(false);
        res.setErrorMsg(errorMsg);
        res.setModule(null);
        return res;
    }

    public static <T> BtripResult<T> returnSuccess(T module) {
        BtripResult<T> res = new BtripResult<T>();
        res.setSuccess(true);
        res.setErrorMsg(null);
        res.setModule(module);
        return res;
    }
}
