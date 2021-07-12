package com.athome.service;


import com.athome.entity.Items;
import com.athome.entity.ItemsImg;
import com.athome.entity.ItemsParam;
import com.athome.entity.ItemsSpec;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface IItemsService {

    /**
     *  根据商品ID查询商品详情
     * @param id
     * @return
     */
     Items queryItemById(String id);

    /**
     * 根据商品ID查询商品图片
     * @param id
     * @return
     */
     List<ItemsImg> queryItemImgsById(String id);

    /**
     * 根据商品ID查询商品规格
     * @param id
     * @return
     */
     List<ItemsSpec> queryItemSpecsById(String id);

    /**
     * 根据商品ID查询商品属性
     * @param id
     * @return
     */
     ItemsParam queryItemsParamById(String id);
}
