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

    private int code;

    private String msg;

    private T data;

    public ResultVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(T data) {
        this.code = 1;
        this.data = data;
    }

    public ResultVO() {
        this.code = 1;
    }
}
