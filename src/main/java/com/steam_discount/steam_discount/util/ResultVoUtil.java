package com.steam_discount.steam_discount.util;

import com.steam_discount.steam_discount.model.ResultVO;

public final class ResultVoUtil {

    private ResultVoUtil() {
    }

    public static <T> ResultVO<T> success() {
        return new ResultVO<>();
    }

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(data);
    }

    public static <T> ResultVO<T> fail(String message) {
        return new ResultVO<>("FAIL", message);
    }

    public static <T> ResultVO<T> fail(String code, String message) {
        return new ResultVO<>(code, message);
    }
}
