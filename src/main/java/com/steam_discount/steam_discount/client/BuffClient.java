package com.steam_discount.steam_discount.client;

import com.steam_discount.steam_discount.client.fallback.BuffClientImpl;
import com.steam_discount.steam_discount.model.GoodsDTO;
import com.steam_discount.steam_discount.model.PageResult;
import com.steam_discount.steam_discount.model.ResultVO;
import com.steam_discount.steam_discount.request.Query163Request;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "buff", url = "${buff.service.url}", fallback = BuffClientImpl.class)
public interface BuffClient {

    @GetMapping("goods")
    ResultVO<PageResult<GoodsDTO>> getGoods(@SpringQueryMap Query163Request request);
}
