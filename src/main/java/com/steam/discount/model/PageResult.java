package com.steam.discount.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    @JsonProperty("items")
    private List<T> items;

    @JsonProperty("page_num")
    private int pageNum;

    @JsonProperty("page_size")
    private int pageSize;

    @JsonProperty("total_count")
    private long totalCount;

    @JsonProperty("total_page")
    private int totalPage;
}
