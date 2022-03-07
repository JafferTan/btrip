package com.jaffer.btrip.util;

public class BtripResultUtils {
    /**
     * 返回失败
     * @param errorMsg
     * @param <T>
     * @return
     */
    public static <T> BtripResult<T> returnFailMsg(String errorMsg) {
        BtripResult<T> res = new BtripResult<T>();
        res.setSuccess(false);
        res.setErrorMsg(errorMsg);
        res.setModule(null);
        return res;
    }

    /**
     * 返回成功
     * @param module
     * @param <T>
     * @return
     */
    public static <T> BtripResult<T> returnSuccess(T module) {
        BtripResult<T> res = new BtripResult<T>();
        res.setSuccess(true);
        res.setErrorMsg(null);
        res.setModule(module);
        return res;
    }
}
