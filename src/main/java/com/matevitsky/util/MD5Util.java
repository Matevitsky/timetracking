package com.matevitsky.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String encryptPassword(String st) {
        String md5Hex = DigestUtils.md5Hex(st);

        return md5Hex;
    }
}
