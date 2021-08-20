package com.athome.bo;

import lombok.Data;

/**
 * @Description: 用于创建订单的BO对象
 * @Author Zengfc
 * @Date 2021/8/17 11:07
 * @Version 1.0
 */
@Data
public class SubmitOrderBO {

    private String userId;
    private String itemSpecIds;
    private String addressId;
    private Integer payMethod;
    private String leftMsg;
}
