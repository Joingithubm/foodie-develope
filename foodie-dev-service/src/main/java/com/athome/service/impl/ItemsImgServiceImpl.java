package com.athome.service.impl;


import com.athome.entity.ItemsImg;
import com.athome.enums.YesOrNo;
import com.athome.mapper.ItemsImgMapper;
import com.athome.service.IItemsImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品图片 服务实现类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
@Service
public class ItemsImgServiceImpl  implements IItemsImgService {

    @Autowired
    ItemsImgMapper itemsImgMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public String queryItemMainByItemId(String itemId) {

        ItemsImg itemsImg = new ItemsImg();
        itemsImg.setItemId(itemId);
        itemsImg.setIsMain(YesOrNo.YES.type);

        ItemsImg img = itemsImgMapper.selectOne(itemsImg);
        return img == null ? "" : img.getUrl();
    }
}
