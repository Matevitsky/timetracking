package com.matevitsky.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String encryptPassword(String st) {

        return DigestUtils.md5Hex(st);
    }

    //TODO: replace with static class
}
