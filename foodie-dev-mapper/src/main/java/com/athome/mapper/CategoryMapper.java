package com.athome.mapper;


import com.athome.entity.Category;
import com.athome.vo.CategoryVO;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

/**
 * <p>
 * 商品分类 Mapper 接口
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface CategoryMapper extends Mapper<Category>  {

    /**
     * 获取二级三级分类
     * @param levelId
     * @return
     */
    List<CategoryVO> getSubCatList(Integer levelId);
}
