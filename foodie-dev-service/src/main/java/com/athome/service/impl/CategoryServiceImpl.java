package com.athome.service.impl;


import com.athome.entity.Category;
import com.athome.mapper.CategoryMapper;
import com.athome.service.ICategoryService;
import com.athome.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
@Service
public class CategoryServiceImpl  implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("type", 1);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public List<CategoryVO> queryTwoLevelCat(Integer levelId) {

        return categoryMapper.getSubCatList(levelId);
    }


}
