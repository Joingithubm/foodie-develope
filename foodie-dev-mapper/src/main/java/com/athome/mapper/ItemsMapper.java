package com.athome.mapper;


import com.athome.entity.Items;
import com.athome.vo.NewItemsVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface ItemsMapper extends Mapper<Items> {

    List<NewItemsVO> findIndexItems(Integer catId);
}
