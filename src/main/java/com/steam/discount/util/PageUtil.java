package com.steam.discount.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class PageUtil {

    public static <T> List<T> fetchAll(BiFunction<Integer, Integer, List<T>> function, Integer pageSize) {
        List<T> result = new ArrayList<>();
        int page = 1;
        while (true) {
            List<T> list = function.apply(page, pageSize);
            if (list == null || list.isEmpty()) {
                break;
            }
            result.addAll(list);
            if (list.size() < pageSize) {
                break;
            }
            page++;
        }
        return result;
    }

    private PageUtil() {
    }
}
