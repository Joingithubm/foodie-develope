package com.athome.enums;


/**
 * @Description:  是否展示 枚举
 * @Author Zengfc
 * @Date 2021/7/1 15:32
 * @Version 1.0
 */
public enum YesOrNo {

    YES(1,"是"),
    NO(0,"否");


    public final Integer type;
    public final String value;

     YesOrNo(Integer type, String value){
        this.type = type;
        this.value = value;
    }
}
