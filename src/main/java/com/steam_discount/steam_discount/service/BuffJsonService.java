package com.steam_discount.steam_discount.service;

import com.steam_discount.steam_discount.model.GoodsDTO;
import com.steam_discount.steam_discount.request.DiscountRequest;

import java.util.Set;

/**
 * steam_discount
 * BuffJsonService
 *
 * @author yoake
 * @date 2021/5/18 9:35
 */
public interface BuffJsonService {

    Set<GoodsDTO> getGoods(DiscountRequest request);

    void saveFile(Set<GoodsDTO> sets);
}
