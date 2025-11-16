package com.steam.discount.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Query163Request {
    private String game;
    private int page_num;
    private int page_size = 80;

    private String rarity;
    private String category_group;
    private String category;
    private String quality;
    private String exterior;
    private String tab;
    @JsonProperty("min_price")
    private double min_price;

    @JsonProperty("max_price")
    private double max_price;

    private String sort_by = "sell_num.desc";
}
