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

    boolean queryUsernameIsExit(String username);

    Users createUser(UserBO userBO);
}
