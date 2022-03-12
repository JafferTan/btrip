package com.jaffer.btrip.util;

public class CodeZipUtil {

    /**
     * 64进制编码
     */
    private static final int SCALE = 6;

    /**
     * 格式化长度
     */
    private static final byte FORMAT_LENGTH = 6;

    /**
     * 最大编码长度
     */
    private static final long MAX_NUM = 1L << 36;

    /**
     * 生成编码
     * @param num
     * @return
     */
    public static String genKey(long num) {
        String code = zipCoding(num);
        byte limit = (byte) (FORMAT_LENGTH - code.length());
        while(limit-- > 0) {
            code = "0" + code;
        }
        return code;
    }

    private static String zipCoding(long num) {
        if (num >= MAX_NUM) {
            throw new IllegalArgumentException("num is too large , max num is " + MAX_NUM);
        }
        String code = "";
        if (num == 0) {
            return "000000";
        }
        while(num > 0) {
            //计算最后一位余数
            code = getChar((byte)(num % (1 << SCALE))) + code;
            //num/64
            num >>= SCALE;
        }
        return code;
    }

    private static char getChar(byte charNum) {
        if (charNum >= 0 && charNum <= 9) {
            return (char)(charNum+48);
        }
        if (charNum >= 10 && charNum <= 35) {
            return (char)(charNum+55);
        }
        if (charNum >= 36 && charNum <= 61) {
            return (char)(charNum+61);
        }
        /**
         * '-' 减号
         */
        if (charNum == 62) {
            return '-';
        }
        /**
         * '+'加号j
         */
        if (charNum == 63) {
            return '+';
        }
        return '@';
    }
}
