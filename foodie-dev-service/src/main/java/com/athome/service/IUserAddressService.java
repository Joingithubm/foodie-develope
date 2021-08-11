package com.athome.service;


import com.athome.bo.AddressBO;
import com.athome.entity.UserAddress;

import java.util.List;

/**
 * <p>
 * 用户地址表 服务类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface IUserAddressService {

    List<UserAddress>  queryAddressByUserId(String userId);

    void saveUserAddress(AddressBO addressBO);

    void  updateUserAddress(AddressBO addressBO);

    void deleteUser(String userId,String addressId);

    void setDefault(String userId, String addressId);
}
