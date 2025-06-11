package com.steam_discount.steam_discount;


import com.steam_discount.steam_discount.enums.Color;
import com.steam_discount.steam_discount.util.EnumUtil;

public class GetJsonMain {

    public static void main(String[] args) {
        String desc = EnumUtil.getDesc(1, Color.class);
        System.out.println(desc);

    }
}
