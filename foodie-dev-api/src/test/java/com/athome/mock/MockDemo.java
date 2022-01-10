package com.athome.mock;

import com.alibaba.testable.core.annotation.MockDiagnose;
import com.alibaba.testable.core.annotation.MockInvoke;
import com.alibaba.testable.core.model.LogLevel;
import com.athome.entity.Users;
import com.athome.mapper.UsersMapper;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: fanchao@dtstack.com
 * @Date: 2022-01-04 2:35 下午
 */
public class MockDemo {

    @MockInvoke(targetClass = UsersMapper.class)
    List<Users> selectByIds(List<Integer> ids){
        Users users = new Users();
        users.setUsername("zhangsan");
        return Lists.newArrayList(users);
    }
    @MockInvoke(targetClass = UsersMapper.class)
    Object selectOneByExample(Object var){
        return new Users();
    }
}
