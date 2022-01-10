package com.athome.service.impl;


import com.athome.bo.UserBO;
import com.athome.entity.Users;
import com.athome.mapper.UsersMapper;
import com.athome.service.IUsersService;
import com.athome.utils.MD5Util;
import io.swagger.models.auth.In;
import org.apache.catalina.User;
import org.apache.ibatis.javassist.bytecode.SignatureAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    private static Random random = new Random();

    @Override
    public boolean queryUsernameIsExit(String username) {
        Example example = new Example(Users.class);
        example.createCriteria().andEqualTo("username",username);
        Users users = usersMapper.selectOneByExample(example);
        return users == null ? false : true;
    }

    @Override
    public Users queryUserForLogin(String username, String password) {

        Example example = new Example(Users.class);
        example.createCriteria().andEqualTo("username",username)
                .andEqualTo("password",password);

        Users users = usersMapper.selectOneByExample(example);
        return users;
    }

    @Override
    public Users createUser(UserBO userBO) {
        Users users  = new Users();
        users.setId(random.nextInt(100)+"");
        users.setUsername(userBO.getUsername());
        users.setPassword(MD5Util.getMD5Str(userBO.getPassword()));
        users.setNickname(userBO.getUsername());
        users.setFace("dsfjoja");
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());

        usersMapper.insert(users);

        return users;
    }


    @Override
    public List<Users> getUsersByIds(List<Integer> ids){

        if (CollectionUtils.isEmpty(ids)){
            throw  new RuntimeException("参数不能为空");
        }

        return usersMapper.selectByIds(ids);
    }

}
