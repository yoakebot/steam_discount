package com.steam.discount.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("对象转JSON失败", e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JSON转对象失败", e);
        }
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new RuntimeException("JSON转List失败", e);
        }
    }

    private JsonUtil() {
    }
}
