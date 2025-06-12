package com.steam_discount.steam_discount.util;

import com.steam_discount.steam_discount.model.ResultVO;

import java.util.Objects;

public class ResponseUtil {

    public static <T> T getDataOrNull(ResultVO<T> result) {
        if (isSuccess(result)) {
            return result.getData();
        }
        return null;
    }

    public static <T> T getDataOrThrow(ResultVO<T> result) {
        if (isSuccess(result)) {
            return result.getData();
        }
        throw new RuntimeException(getMsg(result));
    }

    public static String getMsg(ResultVO<?> result) {
        return (result != null && result.getMsg() != null) ? result.getMsg() : "request fail";
    }

    public static boolean isSuccess(ResultVO<?> result) {
        return result != null && Objects.equals(result.getCode(), "OK") && result.getData() != null;
    }

    private ResponseUtil() {
    }
}
