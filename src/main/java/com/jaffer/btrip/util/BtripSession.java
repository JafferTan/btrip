package com.jaffer.btrip.util;

import javax.servlet.http.HttpServletRequest;

public class BtripSession {
    public static String getString(HttpServletRequest request , String str) {
        return (String) request.getSession().getAttribute(str);
    }
}
