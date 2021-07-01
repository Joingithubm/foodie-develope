package com.athome.vo;

import lombok.Data;

import javax.persistence.Id;
import java.util.List;

/**
 * @Description: 二级分类VO
 * @Author Zengfc
 * @Date 2021/7/1 16:16
 * @Version 1.0
 */
@Data
public class CategoryVO {

    private Integer id;

    /**
     * 分类名称 分类名称
     */
    private String name;

    /**
     * 分类类型 分类得类型，
     1:一级大分类
     2:二级分类
     3:三级小分类
     */
    private Integer type;

    /**
     * 父id 父id 上一级依赖的id，1级分类则为0，二级三级分别依赖上一级
     */
    private Integer fatherId;

    private List<SubCategoryVO> subCatList;
}
