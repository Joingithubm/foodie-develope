package com.athome.service.impl;


import com.athome.entity.ItemsComments;
import com.athome.mapper.ItemsCommentsMapper;
import com.athome.service.IItemsCommentsService;
import com.athome.utils.PagedGridResult;
import com.athome.vo.ItemCommentVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品评价表 服务实现类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
@Service
public class ItemsCommentsServiceImpl implements IItemsCommentsService {

    @Autowired
    ItemsCommentsMapper itemsCommentsMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPageComments(String itemId, Integer level, Integer pageNum, Integer pageSize) {

        Map<String,Object> param = new HashMap<>();
        param.put("itemId",itemId);
        param.put("level",level);

        PageHelper.startPage(pageNum,pageSize);
        List<ItemsComments> comment = itemsCommentsMapper.findComment(param);

        PagedGridResult grid = setPagedGridResult(comment, pageNum);

        return grid;
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
}
