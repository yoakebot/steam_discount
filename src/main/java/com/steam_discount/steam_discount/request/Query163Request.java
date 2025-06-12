package com.steam_discount.steam_discount.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Query163Request {
    private String game;
    @JsonProperty("page_num")
    private int pageNum;

    private String rarity;
    @JsonProperty("category_group")
    private String categoryGroup;
    private String quality;
    private String exterior;
    private String tab;
    @JsonProperty("min_price")
    private double minPrice;

    @JsonProperty("max_price")
    private double maxPrice;
    @JsonProperty("_")
    private long timestamp;
}
