package com.athome.bo;

import lombok.Data;

/**
 * @Description: 收货地址
 * @Author Zengfc
 * @Date 2021/8/11 16:37
 * @Version 1.0
 */
@Data
public class AddressBO {
    private String addressId;
    private String userId;
    private String receiver;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detail;
}
