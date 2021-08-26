package com.steam_discount.steam_discount.service;

/**
 * steam_discount
 * BuffJsonService
 *
 * @author yoake
 * @date 2021/5/18 9:35
 */
public interface BuffJsonService {

    /**
     * 结果写json文件
     *
     * @param customDiscount 自定义出售折扣
     */
    void writeJson(double customDiscount);
}
