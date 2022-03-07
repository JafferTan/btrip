package com.jaffer.btrip.util;

public class BtripResultUtil {
    public static BtripResult returnFailMsg(String errorMsg) {
        BtripResult res = new BtripResult();
        res.setSuccess(false);
        res.setErrorMsg(errorMsg);
        res.setModule(null);
        return res;
    }
}
