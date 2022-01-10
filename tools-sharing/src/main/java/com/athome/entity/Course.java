package com.athome.entity;

import lombok.Data;

/**
 * @Description:
 * @Author: fanchao@dtstack.com
 * @Date: 2022-01-04 7:45 下午
 */
@Data
public class Course {
    private Integer cid;
    private String cname;
    private Integer userId;
    private String cstatus;
}
