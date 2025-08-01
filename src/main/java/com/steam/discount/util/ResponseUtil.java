package com.steam.discount.util;

import com.steam.discount.exception.BizException;
import com.steam.discount.model.ResultVO;

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
        throw new BizException(getMsg(result));
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
