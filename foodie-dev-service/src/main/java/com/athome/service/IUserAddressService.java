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
    /**
     *  根据用户id查询地址列表
     * @param userId
     * @return
     */
    List<UserAddress>  queryAddressByUserId(String userId);

    /**
     * 保存用户地址
     * @param addressBO
     */
    void saveUserAddress(AddressBO addressBO);

    /**
     * 更新用户地址
     * @param addressBO
     */
    void  updateUserAddress(AddressBO addressBO);

    /**
     * 删除地址
     * @param userId
     * @param addressId
     */
    void deleteUser(String userId,String addressId);

    /**
     * 设置默认收货地址
     * @param userId
     * @param addressId
     */
    void setDefault(String userId, String addressId);

    /**
     * 根据用户id 和地址id获取地址
      * @param userId
     * @param addressId
     * @return
     */
    UserAddress queryAddressByUserIdAndId(String userId, String addressId);

}
