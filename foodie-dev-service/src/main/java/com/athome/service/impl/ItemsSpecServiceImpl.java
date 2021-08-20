package com.athome.service.impl;


import com.athome.entity.ItemsSpec;
import com.athome.mapper.ItemsSpecMapper;
import com.athome.service.IItemsSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品规格 服务实现类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
@Service
public class ItemsSpecServiceImpl implements IItemsSpecService {

    @Autowired
    ItemsSpecMapper itemsSpecMapper;



    @Override
    public ItemsSpec queryItemSpecById(String specId) {
        ItemsSpec itemsSpec = new ItemsSpec();
        itemsSpec.setId(specId);
        return itemsSpecMapper.selectOne(itemsSpec);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public  void decreaseItemSpecStock(String specId, Integer buyCount) {
        // 1. synchronized 锁方法：集群方式下不起作用，且效率低下
        // 2. 锁数据库：不推荐使用，导致数据库性能低下
        // 3. 分布式锁： 推荐使用 zookeeper,redis

        // lockUtil.getLock();  -- 加锁

        // 1. 查询库存
        int stock = 10;

        // 2. 判断库存，是否能够减到0一下
        if (stock - buyCount < 0){

        }

        // lockUtil.unLock()   -- 解锁
        //int isSuccess =
         itemsSpecMapper.decreaseItemSpecStock(specId, buyCount);
//        if(isSuccess != 1){
//            throw new RuntimeException("扣除库存失败，原因是库存不足！");
//        }


    }
}
