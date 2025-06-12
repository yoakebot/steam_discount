package com.steam_discount.steam_discount.model;

import lombok.Data;

/**
 * steam_discount
 * Msg
 *
 * @author yoake
 * @date 2021/5/18 14:58
 */
@Data
public class ResultVO<T> {

    private String code;

    private String msg;

    private T data;

    public ResultVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(T data) {
        this.code = "OK";
        this.data = data;
    }

    public ResultVO() {
        this.code = "OK";
    }
}
