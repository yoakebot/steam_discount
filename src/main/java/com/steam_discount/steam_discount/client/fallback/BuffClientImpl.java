package com.steam_discount.steam_discount.client.fallback;

import com.steam_discount.steam_discount.client.BuffClient;
import com.steam_discount.steam_discount.model.GoodsDTO;
import com.steam_discount.steam_discount.model.PageResult;
import com.steam_discount.steam_discount.model.ResultVO;
import com.steam_discount.steam_discount.request.Query163Request;
import com.steam_discount.steam_discount.util.ResultVoUtil;
import org.springframework.stereotype.Component;

@Component
public class BuffClientImpl implements BuffClient {
    @Override
    public ResultVO<PageResult<GoodsDTO>> getGoods(Query163Request request) {
        return ResultVoUtil.fail("暂时请求不到");
    }
}
