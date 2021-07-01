package com.athome.service;


import com.athome.bo.UserBO;
import com.athome.entity.Users;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface IUsersService {

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    boolean queryUsernameIsExit(String username);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    Users queryUserForLogin(String username, String password);

    /**
     * 创建用户
     * @param userBO
     * @return
     */
    Users createUser(UserBO userBO);
}
