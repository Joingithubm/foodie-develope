package com.athome.controller;


import com.athome.bo.UserBO;
import com.athome.entity.Users;
import com.athome.restful.CommonResult;
import com.athome.restful.ResultCode;
import com.athome.service.IUsersService;
import com.athome.utils.CookieUtils;
import com.athome.utils.JsonUtils;
import com.athome.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
@Api(value = "注册登录", tags = "用户登录注册相关的api")
@RestController
@Slf4j
@RequestMapping("/passport")
public class UsersController {

    @Autowired
    IUsersService iUsersService;

    @ApiOperation(value = "用户名是否重复")
    @GetMapping("/usernameIsExist")
    public CommonResult usernameIsExit(String username){
        if (StringUtils.isBlank(username)) {
            return CommonResult.error(ResultCode.PARAM_IS_BLANK);
        }
        boolean isExit = iUsersService.queryUsernameIsExit(username);
        if (!isExit){
            return CommonResult.error(ResultCode.SUCCESS);
        }
        return CommonResult.error(ResultCode.SERVER_COMMON_ERROR);
    }

    @ApiOperation(value = "创建用户")
    @PostMapping("/regist")
    public CommonResult createUser(@RequestBody UserBO userBO){

        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        if (StringUtils.isBlank(username)||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPassword)){
            return CommonResult.error(ResultCode.SERVER_COMMON_ERROR);
        }

        if (!password.equals(confirmPassword)){
            return CommonResult.error(ResultCode.SERVER_COMMON_ERROR);
        }

        boolean isExit = iUsersService.queryUsernameIsExit(username);
        if (isExit){
            return CommonResult.error(ResultCode.SERVER_COMMON_ERROR);
        }
        Users user = iUsersService.createUser(userBO);
        return CommonResult.success(user);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UserBO userBO,
                              HttpServletRequest request,
                              HttpServletResponse response){
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        if (StringUtils.isBlank(username)||
                StringUtils.isBlank(password)){
            return CommonResult.error(ResultCode.SERVER_COMMON_ERROR);
        }

        Users users = iUsersService.queryUserForLogin(username, MD5Util.getMD5Str(password));
        if (users == null){
            return CommonResult.error(ResultCode.SERVER_COMMON_ERROR);
        }

        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(users), true);

        return CommonResult.success(users);
    }

    @ApiOperation(value = "用户退出")
    @PostMapping("/logout")
    public CommonResult logout(@RequestParam String userId,
                               HttpServletRequest request,
                               HttpServletResponse response){
        //清除cookie
        CookieUtils.deleteCookie(request,response,"user");

        return CommonResult.success();
    }
}
