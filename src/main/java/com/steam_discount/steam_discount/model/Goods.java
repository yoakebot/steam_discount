package com.steam_discount.steam_discount.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * steam_discount
 * Goods
 *
 * @author yoake
 * @date 2021/5/17 23:47
 */
@Data
@Accessors(chain = true)
public class Goods implements Serializable, Comparable<Goods> {

    private static final long serialVersionUID = -9000807055864373573L;

    private String id;

    private String game;

    private String name;
    /**
     * buff 最高求购价格
     */
    private Double buyMaxPrice;

    /**
     * buff 求购数
     */
    private Integer bugNum;


    /**
     * buff 快速出售价格
     */
    private Double quickPrice;

    /**
     * buff 当前最低价格
     */
    private Double sellMinPrice;

    /**
     * buff 出售数量
     */
    private Integer sellNum;

    /**
     * Steam 收购价格
     */
    private Double steamPriceCny;
    private Double steamPriceCnyProfit;

    /**
     * 折扣
     */
    private Double discount;

    /**
     * 税后折扣
     */
    private Double discountAfterTax;

    /**
     * 税后余额
     */
    private Double steamBalanceAfterTax;
    private Double steamBalanceAfterTaxProfit;

    /**
     * 出余额折扣
     */
    private Double discountSellThird;

    /**
     * 出余额金额
     */
    private Double SteamPriceSellThird;
    private Double SteamPriceSellThirdProfit;

    private String buffUrl;

    @Override
    public int compareTo(Goods goods) {
        if (this.discount > goods.getDiscount()) {
            return 1;
        } else if (this.discount.equals(goods.getDiscount())) {
            return this.id.compareTo(goods.getId());
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;

        Goods goods = (Goods) o;

        return id.equals(goods.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
