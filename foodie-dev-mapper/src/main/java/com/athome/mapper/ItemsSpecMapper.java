package com.athome.mapper;


import com.athome.entity.ItemsSpec;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>
 * 商品规格 Mapper 接口
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface ItemsSpecMapper extends Mapper<ItemsSpec> {

    void decreaseItemSpecStock(@Param("itemSpecId") String itemSpecId, @Param("buyCounts") Integer buyCounts);
}
