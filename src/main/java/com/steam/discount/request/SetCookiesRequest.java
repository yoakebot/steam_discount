package com.steam.discount.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SetCookiesRequest {
    @Schema(defaultValue = "session=1-dUoMjaofClt14Sz-alZ0U5Ef-h9HcMZax7_MxyZdvCJh2046405441; HttpOnly; Path=/")
    private String session;
}
