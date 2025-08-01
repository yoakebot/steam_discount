package com.steam.discount.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record Boy(
        @Schema(description = "id")
        String id,
        String name,
        String city,
        LocalDateTime time) {
    public Boy(String id, String name, String city) {
        this(id, name, city, LocalDateTime.now());
    }
}