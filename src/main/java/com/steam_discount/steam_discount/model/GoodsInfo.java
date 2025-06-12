package com.steam_discount.steam_discount.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GoodsInfo {
    @Schema(description = "Steam 收购价格")
    @JsonProperty("steam_price_cny")
    private Double steamPriceCny;
}
