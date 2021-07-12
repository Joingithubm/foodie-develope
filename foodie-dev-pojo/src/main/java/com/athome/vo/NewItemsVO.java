package com.athome.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/7/2 10:07
 * @Version 1.0
 */
@Data
public class NewItemsVO {
    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;
    private List<SimpleItemsVO> simpleItemList;
}
