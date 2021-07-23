package com.athome.enums;

import io.swagger.models.auth.In;

/**
 * @Description:  商品评价等级
 * @Author Zengfc
 * @Date 2021/7/22 10:45
 * @Version 1.0
 */
public enum CommentLevel {

    GOOD(1,"好评"),
    NORMAL(2,"中评"),
    BAD(3,"差评");

    public final Integer type;
    public final String value;

    CommentLevel(Integer type, String value){
        this.type = type;
        this.value = value;
    }

}
