package com.athome.service;


import com.athome.entity.Category;
import com.athome.vo.CategoryVO;

import java.util.List;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface ICategoryService {

    /**
     * 查询一级分类
     * @return
     */
     List<Category> queryAllRootLevelCat();

    /**
     *  获取二三级分类
     * @param levelId
     * @return
     */
     List<CategoryVO> queryTwoLevelCat(Integer levelId);

}
