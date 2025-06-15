package com.steam.discount.enums;

import lombok.Getter;

@Getter
public enum QualityEnum implements CommonEnum {

    UNLIMITED(0, ""),
    NORMAL(1, "normal"),
    TOURNAMENT(2, "tournament"),
    STRANGE(3, "strange"),
    UNUSUAL(4, "unusual"),
    UNUSUAL_STRANGE(5, "unusual_strange");

    private final int code;
    private final String desc;

    QualityEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
