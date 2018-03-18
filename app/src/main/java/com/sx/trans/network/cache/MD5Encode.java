package com.sx.trans.network.cache;

import java.security.MessageDigest;

/**
 * 作者 : 刘朝,
 * on 2017/8/31,
 * GitHub : https://github.com/liuzhao1006
 */

public class MD5Encode {
    public static String encode(String string) throws Exception {
        byte[] hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
