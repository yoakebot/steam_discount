package com.steam_discount.steam_discount.model;

import io.swagger.v3.oas.annotations.media.Schema;

public record Boy (
        @Schema(description = "id")
        String id,
        String name,
        String city) {
}
