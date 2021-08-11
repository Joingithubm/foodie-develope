package com.athome.mapper;


import com.athome.entity.ItemsComments;
import com.athome.vo.ItemCommentVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品评价表 Mapper 接口
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface ItemsCommentsMapper extends Mapper<ItemsComments> {

    List<ItemCommentVO> findComment(Map<String,Object> map);
}
