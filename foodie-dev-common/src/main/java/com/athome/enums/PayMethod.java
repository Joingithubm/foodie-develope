package com.athome.enums;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/8/19 10:53
 * @Version 1.0
 */
public enum PayMethod {

    WEIXIN(1,"微信"),
    ALIPAY(2,"支付宝");

    public final Integer type;
    public final String value;

    PayMethod(Integer type,String value){
            this.type = type;
            this.value = value;
    }
}
