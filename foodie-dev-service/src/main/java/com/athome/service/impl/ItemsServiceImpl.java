package com.athome.service.impl;


import com.athome.entity.Items;
import com.athome.entity.ItemsImg;
import com.athome.entity.ItemsParam;
import com.athome.entity.ItemsSpec;
import com.athome.mapper.ItemsImgMapper;
import com.athome.mapper.ItemsMapper;
import com.athome.mapper.ItemsParamMapper;
import com.athome.mapper.ItemsSpecMapper;
import com.athome.service.IItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
@Service
public class ItemsServiceImpl  implements IItemsService {

    @Autowired
    ItemsMapper itemsMapper;
    @Autowired
    ItemsParamMapper itemsParamMapper;
    @Autowired
    ItemsImgMapper itemsImgMapper;
    @Autowired
    ItemsSpecMapper itemsSpecMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String id) {

        Items items = new Items();
        items.setId(id);

        return itemsMapper.selectOne(items);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgsById(String id) {

        Example example  = new Example(ItemsImg.class);
        example.createCriteria().andEqualTo("itemId",id);

        return itemsImgMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecsById(String id) {

        Example example = new Example(ItemsSpec.class);
        example.createCriteria().andEqualTo("itemId",id);

        return itemsSpecMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemsParamById(String id) {

        Example example = new Example(ItemsParam.class);
        example.createCriteria().andEqualTo("itemId",id);

        return itemsParamMapper.selectOneByExample(example);
    }
}
