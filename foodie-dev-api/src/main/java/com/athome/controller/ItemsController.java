package com.athome.controller;

import com.athome.entity.Items;
import com.athome.entity.ItemsImg;
import com.athome.entity.ItemsParam;
import com.athome.entity.ItemsSpec;
import com.athome.restful.CommonResult;
import com.athome.restful.ResultCode;
import com.athome.service.IItemsService;
import com.athome.vo.ItemInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/7/12 15:26
 * @Version 1.0
 */

@Api(value = "商品", tags="商品相关api")
@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    IItemsService itemsService;

    @ApiOperation(value = "获取商品详情")
    @RequestMapping("/info/{itemId}")
    public CommonResult itemsDetail(@PathVariable("itemId")String itemId){
        if (StringUtils.isEmpty(itemId)){
            return CommonResult.error(ResultCode.SERVER_COMMON_ERROR);
        }

        Items items = itemsService.queryItemById(itemId);
        List<ItemsSpec> itemsSpecs = itemsService.queryItemSpecsById(itemId);
        List<ItemsImg> itemsImgs = itemsService.queryItemImgsById(itemId);
        ItemsParam itemsParam = itemsService.queryItemsParamById(itemId);

        ItemInfoVO infoVO = new ItemInfoVO();
        infoVO.setItems(items);
        infoVO.setItemsSpec(itemsSpecs);
        infoVO.setItemsImg(itemsImgs);
        infoVO.setItemsParam(itemsParam);

        return CommonResult.success(infoVO);
    }

}
