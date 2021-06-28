package com.athome.service.impl;


import com.athome.bo.UserBO;
import com.athome.entity.Users;
import com.athome.mapper.UsersMapper;
import com.athome.service.IUsersService;
import com.athome.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
@Service
public class UsersServiceImpl implements IUsersService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public boolean queryUsernameIsExit(String username) {
        Example example = new Example(Users.class);
        example.createCriteria().andEqualTo("username",username);
        Users users = usersMapper.selectOneByExample(example);
        return users == null ? false : true;
    }

    @Override
    public Users createUser(UserBO userBO) {
        Users users  = new Users();
        users.setId("2");
        users.setUsername(userBO.getUsername());
        users.setPassword(MD5Util.getMD5Str(userBO.getPassword()));
        users.setNickname(userBO.getUsername());
        users.setFace("dsfjoja");
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());

        usersMapper.insert(users);

        return users;
    }
}
