package com.steam_discount.steam_discount.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * steam_discount
 * Msg
 *
 * @author yoake
 * @date 2021/5/18 14:58
 */
@Data
@Accessors
public class Msg {

    private int code = 0;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    public static Msg success() {
        return new Msg();
    }

    public static Msg fail(String message) {
        return new Msg(-1, message);
    }

    public Msg add(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public Msg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Msg() {
    }
}
