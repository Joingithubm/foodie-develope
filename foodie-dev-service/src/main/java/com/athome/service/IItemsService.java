package com.athome.service;


import com.athome.entity.Items;
import com.athome.entity.ItemsImg;
import com.athome.entity.ItemsParam;
import com.athome.entity.ItemsSpec;
import com.athome.utils.PagedGridResult;
import com.athome.vo.CommentLevelCountsVo;
import com.athome.vo.ShopcartVO;

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

    /**
     * 根据商品id ，查询商品评价等级
     * @param itemId
     * @return
     */
     CommentLevelCountsVo queryCommentCounts(String itemId);

    /**
     * 分页查询搜索商品页
     * @param keyword
     * @param sort
     * @param pageSize
     * @param pageNum
     * @return
     */
     PagedGridResult querySearchItems(String keyword, String sort, Integer pageSize, Integer pageNum);

    /**
     * 分页查询搜索商品页
     * @param keyword
     * @param sort
     * @param pageSize
     * @param pageNum
     * @return
     */
    PagedGridResult querySearchItemsByCatId(String keyword, String sort, Integer pageSize, Integer pageNum);

    /**
     * 查询购物车规格
     * @param specId
     * @return
     */
    List<ShopcartVO> queryShopcartSpec(String specId);
}
