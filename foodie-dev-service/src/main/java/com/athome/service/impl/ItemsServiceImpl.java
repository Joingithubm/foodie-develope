package com.athome.service.impl;


import com.athome.entity.*;
import com.athome.enums.CommentLevel;
import com.athome.mapper.*;
import com.athome.service.IItemsService;
import com.athome.vo.CommentLevelCountsVo;
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
    @Autowired
    ItemsCommentsMapper itemsCommentsMapper;

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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountsVo queryCommentCounts(String itemId) {
        Integer goodCount = getCommentCounts(itemId, CommentLevel.GOOD.type);
        Integer normalCount = getCommentCounts(itemId, CommentLevel.NORMAL.type);
        Integer badCount = getCommentCounts(itemId, CommentLevel.BAD.type);
        Integer total = goodCount+normalCount+badCount;

        CommentLevelCountsVo countsVo = new CommentLevelCountsVo();
        countsVo.setGoodCounts(goodCount);
        countsVo.setNormalCounts(normalCount);
        countsVo.setBadCounts(badCount);
        countsVo.setTotalCounts(total);
        return countsVo;
    }


    private Integer getCommentCounts(String itemId, Integer type){

        ItemsComments conditon = new ItemsComments();
        conditon.setItemId(itemId);
        if (type != null){
            conditon.setCommentLevel(type);
        }

        return itemsCommentsMapper.selectCount(conditon);
    }




}
