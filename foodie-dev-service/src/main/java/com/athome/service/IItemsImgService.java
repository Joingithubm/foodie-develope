package com.athome.service;


/**
 * <p>
 * 商品图片 服务类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface IItemsImgService {

    /**
     * 根据商品id 查询商品主图
     * @param itemId
     * @return
     */
    String queryItemMainByItemId(String itemId);
}
