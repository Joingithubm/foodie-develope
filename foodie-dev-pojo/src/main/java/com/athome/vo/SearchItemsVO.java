package com.athome.vo;

import lombok.Data;

/**
 * @Description:  搜索商品
 * @Author Zengfc
 * @Date 2021/8/11 10:26
 * @Version 1.0
 */
@Data
public class SearchItemsVO {

    private String itemId;
    private String itemName;
    /**
     * 销量
     */
    private int sellCounts;
    private String imgUrl;
    /**
     * 以分为单位
     */
    private int price;
}
