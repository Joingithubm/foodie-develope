package com.athome.service;


import com.athome.utils.PagedGridResult;
import com.athome.vo.ItemCommentVO;

import java.util.List;

/**
 * <p>
 * 商品评价表 服务类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface IItemsCommentsService  {

    /**
     *  查询商品评论详情
     * @param itemId
     * @param level
     * @return
     */
    PagedGridResult queryPageComments(String itemId, Integer level, Integer pageNum, Integer pageSize);
}
