package com.steam_discount.steam_discount.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@Accessors(chain = true)
public class GoodsDTO implements Serializable, Comparable<GoodsDTO> {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("game")
    private String game;

    @Schema(description = "buff 最高求购价格")
    @JsonProperty("buy_max_price")
    private Double buyMaxPrice;

    @Schema(description = "buff 求购数")
    @JsonProperty("buy_num")
    private Integer bugNum;

    @Schema(description = "buff 快速出售价格")
    @JsonProperty("quick_price")
    private Double quickPrice;

    @Schema(description = "buff 当前最低价格")
    @JsonProperty("sell_min_price")
    private Double sellMinPrice;

    @Schema(description = "buff 出售数量")
    @JsonProperty("sell_num")
    private Integer sellNum;

    @JsonProperty("goods_info")
    GoodsInfo goodsInfo;

    @Schema(description = "Steam 收购利润")
    private Double steamPriceCnyProfit;
    @Schema(description = "折扣")
    private Double discount;
    @Schema(description = "税后折扣")
    private Double discountAfterTax;
    @Schema(description = "税后余额")
    private Double steamBalanceAfterTax;
    @Schema(description = "税后利润")
    private Double steamBalanceAfterTaxProfit;
    @Schema(description = "出余额折扣")
    private Double discountSellThird;
    @Schema(description = "出余额金额")
    private Double steamPriceSellThird;
    @Schema(description = "出余额利润")
    private Double steamPriceSellThirdProfit;
    @Schema(description = "buff链接")
    private String buffUrl;

    @Override
    public int compareTo(GoodsDTO goods) {
        if (this.discount > goods.getDiscount()) {
            return 1;
        } else if (this.discount.equals(goods.getDiscount())) {
            return this.id.compareTo(goods.getId());
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;  // 类型检查
        GoodsDTO goods = (GoodsDTO) obj;
        return this.id.equals(goods.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
