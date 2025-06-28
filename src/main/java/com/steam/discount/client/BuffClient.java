package com.steam.discount.client;

import com.steam.discount.client.fallback.BuffClientImpl;
import com.steam.discount.config.FeignConfig;
import com.steam.discount.model.GoodsDTO;
import com.steam.discount.model.PageResult;
import com.steam.discount.model.ResultVO;
import com.steam.discount.request.Query163Request;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "buff", url = "${buff.service.url}", fallback = BuffClientImpl.class, configuration = FeignConfig.class)
public interface BuffClient {

    @GetMapping("goods")
    ResultVO<PageResult<GoodsDTO>> getGoods(@SpringQueryMap Query163Request request);
}
