package com.steam_discount.steam_discount.util;

import java.math.BigDecimal;

/**
 * discount
 * BigDecimalUtil
 *
 * @author yoake
 * @date 2021/5/19 14:45
 */
public class BigDecimalUtil {

    public static double evalPrice(double d) {
        return new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double evalDiscount(double d) {
        return new BigDecimal(d).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
