package com.athome.service;


import com.athome.entity.Carousel;

import java.util.List;

/**
 * <p>
 * 轮播图 服务类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface ICarouselService  {

    /**
     * 查询轮播图
     * @param isShow
     * @return
     */
    List<Carousel> queryAll(Integer isShow);

}
