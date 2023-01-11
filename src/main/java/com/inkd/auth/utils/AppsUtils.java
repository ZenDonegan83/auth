package com.inkd.auth.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppsUtils {

    public static Integer getRandomInt() {
        int min = 1000;
        int max = 9999;

        int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);

        return randomInt;
    }

    public static  <T> T jsonToObjectMapper(String json, Class<T> tClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        T t = null;

        try {
            //Map JSON string to objects
            t = objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return t;
    }
}
