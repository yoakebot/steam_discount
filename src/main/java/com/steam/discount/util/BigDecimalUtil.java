package com.steam.discount.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * discount
 * BigDecimalUtil
 *
 * @author yoake
 * @date 2021/5/19 14:45
 */
public class BigDecimalUtil {

    public static double evalPrice(double d) {
        return BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static double evalDiscount(Double d1, Double d2) {
        if (d1 == null || d2 == null || d2 == 0) {
            return 0;
        }
        return BigDecimal.valueOf(d1 / d2).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }

    private BigDecimalUtil() {
    }
}
