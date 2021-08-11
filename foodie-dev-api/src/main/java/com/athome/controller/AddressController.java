package com.athome.controller;

import com.athome.bo.AddressBO;
import com.athome.restful.CommonResult;
import com.athome.restful.ResultCode;
import com.athome.service.IUserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Description: 收货地址
 * @Author Zengfc
 * @Date 2021/6/28 12:11
 * @Version 1.0
 */
@Api(value = "收货地址",tags = "收货地址相关API")
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    IUserAddressService userAddressService;


    @ApiOperation(value = "收货地址列表")
    @PostMapping("/list")
    public CommonResult list(@RequestParam String userId){

        if (StringUtils.isBlank(userId)){
            return CommonResult.success();
        }

        return CommonResult.success(userAddressService.queryAddressByUserId(userId));

    }

    @ApiOperation(value = "新增收货地址")
    @PostMapping("/add")
    public CommonResult add(@RequestBody AddressBO addressBO){

        if (addressBO == null){
            return CommonResult.success(ResultCode.SERVER_COMMON_ERROR);
        }
        userAddressService.saveUserAddress(addressBO);
        return CommonResult.success();

    }

    @ApiOperation(value = "更新收货地址")
    @PostMapping("/update")
    public CommonResult update(@RequestBody AddressBO addressBO){

        if (addressBO == null){
            return CommonResult.success(ResultCode.SERVER_COMMON_ERROR);
        }
        userAddressService.updateUserAddress(addressBO);
        return CommonResult.success();

    }

    @ApiOperation(value = "删除收货地址")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam String userId,
                               @RequestParam String addressId){

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return CommonResult.success(ResultCode.SERVER_COMMON_ERROR);
        }
        userAddressService.deleteUser(userId,addressId);
        return CommonResult.success();


    }

    @ApiOperation(value = "设置默认收货地址")
    @PostMapping("/setDefalut")
    public CommonResult setDefault(@RequestParam String userId,
                                   @RequestParam String addressId){

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return CommonResult.success(ResultCode.SERVER_COMMON_ERROR);
        }
        userAddressService.setDefault(userId,addressId);
        return CommonResult.success();

    }

}
