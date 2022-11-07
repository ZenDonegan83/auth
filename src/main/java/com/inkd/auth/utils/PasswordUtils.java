package com.inkd.auth.utils;

public class PasswordUtils {

    public static String generateRandomPlainPassword(Integer length) {

        final String ALPHA_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            int ndx = (int) (Math.random() * ALPHA_NUM.length());
            sb.append(ALPHA_NUM.charAt(ndx));
        }

        return sb.toString();
    }
}
