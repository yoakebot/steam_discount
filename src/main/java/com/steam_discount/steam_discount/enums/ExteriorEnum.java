package com.steam_discount.steam_discount.enums;

public enum ExteriorEnum implements CommonEnum {
    UNLIMITED(0, ""),
    FACTORY_NEW(1, "wearcategory0"),
    FIELD_TESTED(2, "wearcategory1"),
    WELL_WORN(3, "wearcategory2"),
    BATTLE_SCARRED(4, "wearcategory3"),
    HEAVILY_WORN(5, "wearcategory4"),
    NO_STICKER(6, "wearcategoryna");

    private final int code;
    private final String desc;

    ExteriorEnum(int code, String desc) {
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
