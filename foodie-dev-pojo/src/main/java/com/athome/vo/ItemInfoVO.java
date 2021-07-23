package com.athome.vo;

import com.athome.entity.Items;
import com.athome.entity.ItemsImg;
import com.athome.entity.ItemsParam;
import com.athome.entity.ItemsSpec;
import lombok.Data;

import java.util.List;

/**
 * @Description: 商品详情vo
 * @Author Zengfc
 * @Date 2021/7/12 15:47
 * @Version 1.0
 */
@Data
public class ItemInfoVO {

    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;
}
