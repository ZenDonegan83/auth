package com.inkd.auth.utils;

public class AppsUtils {

    public static Integer getRandomInt() {
        int min = 1000;
        int max = 9999;

        int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);

        return randomInt;
    }
}
