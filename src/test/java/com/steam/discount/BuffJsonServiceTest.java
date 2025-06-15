package com.steam.discount;

import com.steam.discount.service.BuffJsonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * steam_discount
 * BuffJsonServiceTest
 *
 * @author yoake
 * @date 2021/5/18 9:49
 */
@SpringBootTest
class BuffJsonServiceTest {

    @Autowired
    private BuffJsonService buffJsonService;

    @Test
    public void getJson() {
    }

    @Test
    public void test2() {
        double buffPrice = 8.34;
        double SteamPrice = 13.11;
        double discount = buffPrice / SteamPrice;
        double discountAfterTax = buffPrice / (SteamPrice * 100/115);
        double discountProfit = 1 - buffPrice / (discountAfterTax * 0.8);
        System.out.println(discount + "\n\r" + discountAfterTax + "\n\r" + discountProfit);
    }

    @Test
    public void test3() {
        StringBuilder sb = new StringBuilder("2132132%s");
        Float f = 10011 / 10f;
        Double d = 10011 / 10d;
        System.out.println(f);
        System.out.println(d);
    }

}