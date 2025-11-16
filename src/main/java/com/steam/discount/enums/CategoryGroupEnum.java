package com.steam.discount.enums;

import lombok.Getter;

@Getter
public enum CategoryGroupEnum implements CommonEnum {

    KNIFE(1, "knife"),
    HANDS(2, "hands"),
    RIFLE(3, "rifle"),
    PISTOL(4, "pistol"),
    SMG(5, "smg"),
    SHOTGUN(6, "shotgun"),
    MACHINE_GUN(7, "machinegun"),
    STICKER(8, "sticker"),
    TYPE_CUSTOM_PLAYER(9, "type_customplayer"),
    CSGO_TYPE_WEAPONCASE(10, "csgo_type_weaponcase");


    private final int code;
    private final String desc;

    CategoryGroupEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
