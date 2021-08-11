package com.athome.service.impl;


import com.athome.entity.*;
import com.athome.enums.CommentLevel;
import com.athome.mapper.*;
import com.athome.service.IItemsService;
import com.athome.utils.PagedGridResult;
import com.athome.vo.CommentLevelCountsVo;
import com.athome.vo.SearchItemsVO;
import com.athome.vo.ShopcartVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult querySearchItems(String keyword, String sort, Integer pageSize, Integer pageNum) {

        Map<String,Object> param = new HashMap<>();
        if (StringUtils.isNotBlank(keyword)){
            param.put("keyword",keyword);
        }
        param.put("sort",sort);

        PageHelper.startPage(pageNum,pageSize);
        List<SearchItemsVO> itemBySearch = itemsMapper.findItemBySearch(param);

        return setPagedGridResult(itemBySearch,pageNum);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult querySearchItemsByCatId(String keyword, String sort, Integer pageSize, Integer pageNum) {

        Map<String,Object> param = new HashMap<>();
        if (StringUtils.isNotBlank(keyword)){
            param.put("catId",keyword);
        }
        param.put("sort",sort);

        PageHelper.startPage(pageNum,pageSize);
        List<SearchItemsVO> itemBySearch = itemsMapper.findItemByCatId(param);

        return setPagedGridResult(itemBySearch,pageNum);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ShopcartVO> queryShopcartSpec(String specId) {

        String[] ids = specId.split(",");
        List<String> list = new ArrayList<>();
        Collections.addAll(list,ids);

        return itemsMapper.findItemsSp(list);
    }

    public PagedGridResult setPagedGridResult(List<?> list, Integer pageNum){
        PageInfo<?> info = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(pageNum);
        grid.setRows(list);
        grid.setTotal(info.getPages());
        grid.setRecords(info.getTotal());

        return grid;
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
