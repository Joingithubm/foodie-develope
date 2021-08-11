package com.athome.controller;

import com.athome.bo.ShopcartBO;
import com.athome.restful.CommonResult;
import com.athome.restful.ResultCode;
import com.athome.service.IItemsService;
import com.athome.vo.ShopcartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/6/28 12:11
 * @Version 1.0
 */
@Api(value = "购物车接口controller",tags = {"购物车接口相关的api"})
@RequestMapping("shopcart")
@RestController
@Slf4j
public class ShopcatController {

    @Autowired
    IItemsService itemsService;

    @ApiOperation(value = "添加商品到购物车")
    @PostMapping("/add")
    public CommonResult add(@RequestParam String userId,
                            @RequestBody ShopcartBO shopcartBO,
                            HttpServletRequest request,
                            HttpServletResponse response){

        if(StringUtils.isBlank(userId)){
            return CommonResult.success(ResultCode.SERVER_COMMON_ERROR);
        }

        log.info("购物车内容：{}",shopcartBO);

        // TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存


        return CommonResult.success();
    }


    @ApiOperation(value = "从购物车中删除商品")
    @PostMapping("/del")
    public CommonResult del(@RequestParam String userId,
                            @RequestParam String itemSpecId,
                            HttpServletRequest request,
                            HttpServletResponse response){

        if(StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)){
            return CommonResult.success(ResultCode.SERVER_COMMON_ERROR);
        }

        log.info("删除成功");
        // TODO 前端用户在界面删除购物车中商品 。登录的情况下，删除商品到购物车，会同时在后端同步购物车到redis缓存


        return CommonResult.success();
    }




}
