package com.steam.discount.service;

import com.steam.discount.model.GoodsDTO;
import com.steam.discount.request.DiscountRequest;

import java.util.Set;

/**
 * steam_discount
 * BuffJsonService
 *
 * @author yoake
 * @date 2021/5/18 9:35
 */
public interface BuffJsonService {

    void getGoods(DiscountRequest request);

    void saveFile(Set<GoodsDTO> sets);
}
