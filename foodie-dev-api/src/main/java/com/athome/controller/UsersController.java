package com.athome.controller;


import com.athome.bo.UserBO;
import com.athome.entity.Users;
import com.athome.restful.CommonResult;
import com.athome.restful.ResultCode;
import com.athome.service.IUsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
@RestController
@RequestMapping("/dev/users")
public class UsersController {

    @Autowired
    IUsersService iUsersService;

    @RequestMapping("/usernameIsExit")
    public CommonResult usernameIsExit(String username){
        if (StringUtils.isBlank(username)) {
            return CommonResult.error(ResultCode.PARAM_IS_BLANK);
        }
        boolean isExit = iUsersService.queryUsernameIsExit(username);
        if (!isExit){
            return CommonResult.error(ResultCode.USER_NOT_EXIST);
        }
        return CommonResult.success();
    }

    @RequestMapping("/createUser")
    public CommonResult createUser(@RequestBody UserBO userBO){

        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        if (StringUtils.isBlank(username)||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPassword)){
            return CommonResult.error(ResultCode.PARAM_IS_BLANK);
        }

        if (!password.equals(confirmPassword)){
            return CommonResult.error(ResultCode.USER_PWD_NOT_EQUALS);
        }

        boolean isExit = iUsersService.queryUsernameIsExit(username);
        if (isExit){
            return CommonResult.error(ResultCode.USER_HAS_EXISTED);
        }
        Users user = iUsersService.createUser(userBO);
        return CommonResult.success(user);
    }

}
