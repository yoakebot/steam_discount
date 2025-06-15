package com.steam.discount.config;

import java.util.List;

public class CookieContextHolder {

    private static final ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();

    public static void setCookies(List<String> cookies) {
        threadLocal.set(cookies);
    }

    public static List<String> getCookies() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

    private CookieContextHolder() {
    }
}
