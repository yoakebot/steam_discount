package com.steam.discount.enums;

import lombok.Getter;

@Getter
public enum RarityEnum implements CommonEnum {

    UNLIMITED_QUALITY(0, ""),

    CONTRABAND(1, "contraband"),

    ANCIENT_WEAPON(2, "legendary_weapon"),

    LEGENDARY_WEAPON(3, "contraband"),

    MYTHICAL_WEAPON(4, "mythical_weapon"),

    RARE_WEAPON(5, "rare_weapon"),

    UNCOMMON_WEAPON(6, "uncommon_weapon"),

    COMMON_WEAPON(7, "common_weapon"),

    ANCIENT(8, "ancient"),

    MYTHICAL(9, "mythical"),

    LEGENDARY(10, "legendary"),

    RARE(11, "rare"),

    COMMON(12, "common");


    private final int code;
    private final String desc;

    RarityEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
