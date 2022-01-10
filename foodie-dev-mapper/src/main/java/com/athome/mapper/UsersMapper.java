package com.athome.mapper;


import com.athome.entity.Users;
import io.swagger.models.auth.In;
import org.apache.catalina.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface UsersMapper extends Mapper<Users> {

    List<Users> selectByIds(List<Integer> ids);

}
