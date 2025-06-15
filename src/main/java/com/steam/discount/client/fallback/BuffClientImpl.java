package com.steam.discount.client.fallback;

import com.steam.discount.client.BuffClient;
import com.steam.discount.model.GoodsDTO;
import com.steam.discount.model.PageResult;
import com.steam.discount.model.ResultVO;
import com.steam.discount.request.Query163Request;
import com.steam.discount.util.ResultVoUtil;
import org.springframework.stereotype.Component;

@Component
public class BuffClientImpl implements BuffClient {
    @Override
    public ResultVO<PageResult<GoodsDTO>> getGoods(Query163Request request) {
        return ResultVoUtil.fail("暂时请求不到");
    }
}
