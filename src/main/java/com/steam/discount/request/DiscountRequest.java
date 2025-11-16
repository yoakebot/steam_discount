package com.steam.discount.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DiscountRequest {

    @Schema(hidden = true)
    private int pageNum = 1;

    @Schema(description = "取总页数")
    private int pageTotal;

    @Schema(description = "最小金额", defaultValue = "200")
    private double minPrice;

    @Schema(description = "最大金额", defaultValue = "300")
    private double maxPrice;
    @Schema(description = "折扣", defaultValue = "0.8")
    private double customDiscount;

    @Schema(description = """
              0: 不限类别
              1: 普通
              2: 纪念品
              3: StatTrak™
              4: ★
              5: ★ StatTrak™
            """, defaultValue = "0")
    private Integer quality;

    @Schema(description = """
              0: 不限品质
              1: 违禁
              2: 隐秘
              3: 保密
              4: 受限
              5: 军规级
              6: 工业级
              7: 消费级
              8: 非凡
              9: 卓越
              10: 奇异
              11: 高级
              12: 普通级
            """, defaultValue = "2")
    private Integer rarity;

    @Schema(description = """
              1: 匕首
              2: 手套
              3: 步枪
              4: 手枪
              5: 微型冲锋枪
              6: 霰弹枪
              7: 机枪
              8: 印花
              9: 探员
              10: 武器箱
            """, defaultValue = "3")
    private Integer categoryGroup;

    @Schema(description = """
              0: 不限外观
              1: 崭新出厂
              2: 略有磨损
              3: 久经沙场
              4: 破损不堪
              5: 战痕累累
              6: 无涂装
            """, defaultValue = "0")
    private Integer exterior;
}
