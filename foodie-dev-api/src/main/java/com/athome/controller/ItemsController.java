package com.athome.controller;

import com.athome.entity.Items;
import com.athome.entity.ItemsImg;
import com.athome.entity.ItemsParam;
import com.athome.entity.ItemsSpec;
import com.athome.restful.CommonResult;
import com.athome.restful.ResultCode;
import com.athome.service.IItemsCommentsService;
import com.athome.service.IItemsService;
import com.athome.utils.PagedGridResult;
import com.athome.vo.CommentLevelCountsVo;
import com.athome.vo.ItemInfoVO;
import com.athome.vo.ShopcartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
public class ItemsController extends BaseController{

    @Autowired
    IItemsService itemsService;

    @Autowired
    IItemsCommentsService itemsCommentsService;

    @ApiOperation(value = "获取商品详情")
    @GetMapping("/info/{itemId}")
    public CommonResult itemsDetail(@PathVariable("itemId")String itemId){
        if (StringUtils.isEmpty(itemId)){
            return CommonResult.error(ResultCode.SERVER_COMMON_ERROR);
        }

        Items items = itemsService.queryItemById(itemId);
        List<ItemsSpec> itemsSpecs = itemsService.queryItemSpecsById(itemId);
        List<ItemsImg> itemsImgs = itemsService.queryItemImgsById(itemId);
        ItemsParam itemsParam = itemsService.queryItemsParamById(itemId);

        ItemInfoVO infoVO = new ItemInfoVO();
        infoVO.setItem(items);
        infoVO.setItemSpecList(itemsSpecs);
        infoVO.setItemImgList(itemsImgs);
        infoVO.setItemParams(itemsParam);

        return CommonResult.success(infoVO);
    }

    @ApiOperation(value = "商品评价等级")
    @GetMapping("/commentLevel")
    public CommonResult commentLevel(@RequestParam("itemId") String itemId){

        if (StringUtils.isEmpty(itemId)){
            CommonResult.error(ResultCode.SERVER_COMMON_ERROR);
        }
        CommentLevelCountsVo levelCountsVo = itemsService.queryCommentCounts(itemId);

        return CommonResult.success(levelCountsVo);
    }

    @ApiOperation(value = "商品评论详情")
    @GetMapping("/comments")
    public CommonResult commonDetail(@RequestParam("itemId") String itemId,
                                     @RequestParam("level") Integer level,
                                     @RequestParam("page") Integer page,
                                     @RequestParam("pageSize") Integer pageSize){

        if (StringUtils.isEmpty(itemId)) {
            return CommonResult.error(ResultCode.SERVER_COMMON_ERROR);
        }
        if(page != null){
            page = COMMENT_PAGE_NUM;
        }
        if (pageSize != null){
            pageSize = COMMENT_PAGE_SIZE;
        }
        PagedGridResult gridResult = itemsCommentsService.queryPageComments(itemId, level, page, pageSize);

        return CommonResult.success(gridResult);
    }

    @ApiOperation(value = "搜索商品")
    @GetMapping("/search")
    public CommonResult searchItems(@RequestParam("keywords") String keywords,
                                     @RequestParam("sort") String sort,
                                     @RequestParam("page") Integer page,
                                     @RequestParam("pageSize") Integer pageSize){

        if(page != null){
            page = COMMENT_PAGE_NUM;
        }
        if (pageSize != null){
            pageSize = SEARCH_ITEMS_PAGE_SIZE;
        }
        PagedGridResult gridResult = itemsService.querySearchItems(keywords, sort, pageSize, page);

        return CommonResult.success(gridResult);
    }

    @ApiOperation(value = "搜索商品")
    @GetMapping("/catItems")
    public CommonResult catItems(@RequestParam("catId") String catId,
                                    @RequestParam("sort") String sort,
                                    @RequestParam("page") Integer page,
                                    @RequestParam("pageSize") Integer pageSize){

        if(page != null){
            page = COMMENT_PAGE_NUM;
        }
        if (pageSize != null){
            pageSize = SEARCH_ITEMS_PAGE_SIZE;
        }
        PagedGridResult gridResult = itemsService.querySearchItemsByCatId(catId, sort, pageSize, page);

        return CommonResult.success(gridResult);
    }


    /**
     *  用于用户长时间未登录网站，刷新购物车中的数据（主要是商品价格）,类似京东
     * @param itemSpecIds
     * @return
     */
    @ApiOperation(value = "根据商品规格id，获取商品最新规格信息")
    @GetMapping("/refresh")
    public CommonResult add(@RequestParam String itemSpecIds){

        if(org.apache.commons.lang3.StringUtils.isBlank(itemSpecIds)){
            return CommonResult.success(ResultCode.SERVER_COMMON_ERROR);
        }

        List<ShopcartVO> shopcartVOS = itemsService.queryShopcartSpec(itemSpecIds);


        return CommonResult.success(shopcartVOS);
    }

}
