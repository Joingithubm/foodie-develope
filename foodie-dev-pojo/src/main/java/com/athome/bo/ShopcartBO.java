package com.athome.bo;

import lombok.Data;

/**
 * @Description: 购物车
 * @Author Zengfc
 * @Date 2021/8/11 15:09
 * @Version 1.0
 */
@Data
public class ShopcartBO {
    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private String buyCounts;
    private String priceDiscount;
    private String priceNormal;
}
