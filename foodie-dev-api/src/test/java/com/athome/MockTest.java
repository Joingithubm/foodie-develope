package com.athome;

import com.alibaba.testable.core.annotation.MockInvoke;
import com.alibaba.testable.core.annotation.MockWith;
import com.alibaba.testable.core.model.ClassType;
import com.athome.entity.Users;
import com.athome.mapper.UsersMapper;
import com.athome.mock.MockDemo;
import com.athome.service.IUsersService;
import com.athome.service.impl.UsersServiceImpl;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.pattern.PathPattern;
import tk.mybatis.mapper.common.example.SelectOneByExampleMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: fanchao@dtstack.com
 * @Date: 2022-01-04 12:11 下午
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockTest {




}
