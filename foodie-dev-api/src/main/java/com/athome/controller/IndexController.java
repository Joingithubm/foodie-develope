package com.athome.controller;

import com.athome.entity.Carousel;
import com.athome.entity.Category;
import com.athome.enums.YesOrNo;
import com.athome.restful.CommonResult;
import com.athome.restful.ResultCode;
import com.athome.service.ICarouselService;
import com.athome.service.ICategoryService;
import com.athome.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 首页接口
 * @Author Zengfc
 * @Date 2021/7/1 15:23
 * @Version 1.0
 */

@Api(value = "首页", tags = "首页相关接口")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    ICarouselService iCarouselService;

    @Autowired
    ICategoryService iCategoryService;

    @ApiOperation(value = "首页轮播图")
    @RequestMapping("/carousel")
    public CommonResult carousel(){

        List<Carousel> result = iCarouselService.queryAll(YesOrNo.YES.type);
        return CommonResult.success(result);
    }

    @ApiOperation(value = "首页一级分类")
    @RequestMapping("cats")
    public CommonResult cats(){
        List<Category> result = iCategoryService.queryAllRootLevelCat();
        return CommonResult.success(result);
    }

    @ApiOperation(value = "获取商品子分类")
    @RequestMapping("/subCat/{rootCatId}")
    public CommonResult subCat(@PathVariable("rootCatId") Integer rootCatId){
        if (rootCatId == null){
            return CommonResult.error(ResultCode.SERVER_COMMON_ERROR);
        }
        List<CategoryVO> result = iCategoryService.queryTwoLevelCat(rootCatId);
        return CommonResult.success(result);
    }
}
