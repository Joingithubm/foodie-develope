package com.athome.mapper;


import com.athome.entity.Items;
import com.athome.vo.NewItemsVO;
import com.athome.vo.SearchItemsVO;
import com.athome.vo.ShopcartVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

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

    List<SearchItemsVO> findItemBySearch(Map<String,Object> map);
    List<SearchItemsVO> findItemByCatId(Map<String,Object> map);

    List<ShopcartVO> findItemsSp(@Param("specList") List<String> list);


}
