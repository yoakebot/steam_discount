package com.steam_discount.steam_discount.enums;

public enum CategoryGroupEnum implements CommonEnum {

    KNIFE(1, "knife"),
    HANDS(2, "hands"),
    RIFLE(3, "rifle"),
    PISTOL(4, "pistol"),
    SMG(5, "smg"),
    SHOTGUN(6, "shotgun"),
    MACHINEGUN(7, "machinegun"),
    STICKER(8, "sticker"),
    TYPE_CUSTOMPLAYER(9, "type_customplayer"),
    OTHER(10, "other");


    private final int code;
    private final String desc;

    CategoryGroupEnum(int code, String desc) {
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
