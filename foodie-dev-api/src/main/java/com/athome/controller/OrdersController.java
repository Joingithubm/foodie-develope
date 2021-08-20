package com.athome.controller;

import com.athome.bo.SubmitOrderBO;
import com.athome.enums.PayMethod;
import com.athome.restful.CommonResult;
import com.athome.restful.ResultCode;
import com.athome.service.IOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

/**
 * @Description: 订单controller
 * @Author Zengfc
 * @Date 2021/6/28 12:11
 * @Version 1.0
 */
@Api(value = "订单相关",tags = "订单相关api")
@RestController
@RequestMapping("/orders")

public class OrdersController {

    Logger log  = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    IOrdersService ordersService;

    @ApiOperation(value = "创建订单")
    @RequestMapping("/create")
    public CommonResult create(@RequestBody SubmitOrderBO submitOrderBO){
        log.info("请求参数：{}",submitOrderBO);

        if (submitOrderBO.getPayMethod().equals(PayMethod.WEIXIN.type)
        && submitOrderBO.getPayMethod().equals( PayMethod.ALIPAY.type)){
            return CommonResult.error(ResultCode.SERVER_COMMON_ERROR,"不支持该方式");
        }

        // 1. 创建订单
        ordersService.createOrders(submitOrderBO);
        // 2. 删除购物车商品
        // 3. 调用支付
        return CommonResult.success();
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }
}
