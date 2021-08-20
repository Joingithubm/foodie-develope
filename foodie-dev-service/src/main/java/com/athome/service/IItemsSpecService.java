package com.athome.service;


import com.athome.entity.ItemsSpec;

/**
 * <p>
 * 商品规格 服务类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface IItemsSpecService  {
    /**
     * 根据id查询商品规格
     * @param specId
     * @return
     */
    ItemsSpec queryItemSpecById(String specId);

    /**
     * 用户减库存
     * @param specId
     * @param buyCount
     */
    void decreaseItemSpecStock(String specId, Integer buyCount);

}
