package com.steam_discount.steam_discount.enums;

/**
 * steam_discount
 * Color
 *
 * @Author yoake
 * @Date 2025/6/7 17:44
 */
public enum Color implements CommonEnum {
    RED(1, "红色"),
    BLUE(2, "蓝色");

    private final int code;
    private final String desc;

    Color(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
