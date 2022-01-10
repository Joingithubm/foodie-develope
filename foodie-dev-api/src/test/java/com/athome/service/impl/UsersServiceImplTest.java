package com.athome.service.impl;

import com.alibaba.testable.core.annotation.MockWith;
import com.athome.entity.Users;
import com.athome.mock.MockDemo;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * @Description:
 * @Author: fanchao@dtstack.com
 * @Date: 2022-01-04 2:53 下午
 */
@MockWith(MockDemo.class)
public class UsersServiceImplTest {


    UsersServiceImpl usersService = new UsersServiceImpl();


    @Test
    public void test(){
        List<Users> usersByIds = usersService.getUsersByIds(Lists.newArrayList(1, 2));
        System.out.println(usersByIds);
    }



    @Test
    public void test1(){
        boolean zhangsan = usersService.queryUsernameIsExit("zhangsan");


        System.out.println(zhangsan);
    }
}
