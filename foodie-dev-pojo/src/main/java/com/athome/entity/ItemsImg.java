package com.athome.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品图片
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ItemsImg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片主键
     */
    private String id;

    /**
     * 商品外键id 商品外键id
     */
    private String itemId;

    /**
     * 图片地址 图片地址
     */
    private String url;

    /**
     * 顺序 图片顺序，从小到大
     */
    private Integer sort;

    /**
     * 是否主图 是否主图，1：是，0：否
     */
    private Integer isMain;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;


}
