package com.athome.service.impl;


import com.athome.entity.Carousel;
import com.athome.mapper.CarouselMapper;
import com.athome.service.ICarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 * 轮播图 服务实现类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
@Service
public class CarouselServiceImpl implements ICarouselService {

    @Autowired
    CarouselMapper carouselMapper;

    @Override
    public List<Carousel> queryAll(Integer isShow) {

        Example example  = new Example(Carousel.class);
        example.orderBy("sort").asc();
        example.createCriteria().andEqualTo("isShow", isShow);

        List<Carousel> list = carouselMapper.selectByExample(example);

        return list;
    }
}
