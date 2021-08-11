package com.athome.service.impl;


import com.athome.bo.AddressBO;
import com.athome.entity.UserAddress;
import com.athome.enums.YesOrNo;
import com.athome.mapper.UserAddressMapper;
import com.athome.service.IUserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户地址表 服务实现类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
@Service
@Slf4j
public class UserAddressServiceImpl  implements IUserAddressService {

    @Autowired
    UserAddressMapper userAddressMapper;

    @Autowired
    Sid sid;

    @Override
    public List<UserAddress> queryAddressByUserId(String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        return userAddressMapper.select(userAddress);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = SQLException.class)
    @Override
    public void saveUserAddress(AddressBO addressBO) {

        int defualt = 0;
        String addressId = sid.nextShort();
        UserAddress userAddress = new UserAddress();

        List<UserAddress> addresses = queryAddressByUserId(addressBO.getUserId());
        if (CollectionUtils.isEmpty(addresses)){
            defualt = 1;
        }

        BeanUtils.copyProperties(addressBO,userAddress);
        userAddress.setId(addressId);
        userAddress.setIsDefault(defualt);
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());

        int insert = userAddressMapper.insert(userAddress);
        log.info("地址新增成功:{}",insert);
    }

    @Transactional(propagation = Propagation.REQUIRED ,rollbackFor = SQLException.class)
    @Override
    public void updateUserAddress(AddressBO addressBO) {

        UserAddress userAddress = new UserAddress();
        String addressId = addressBO.getAddressId();

        BeanUtils.copyProperties(addressBO,userAddress);
        userAddress.setUpdatedTime(new Date());
        userAddress.setId(addressId);

        Example example = new Example(UserAddress.class);
        example.createCriteria().andEqualTo("id",addressId);
        int i = userAddressMapper.updateByExampleSelective(userAddress,example);
        log.info("地址更新成功：{}",i);
    }

    @Transactional(propagation = Propagation.REQUIRED ,rollbackFor = SQLException.class)
    @Override
    public void deleteUser(String userId, String addressId) {

        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setId(addressId);

        int delete = userAddressMapper.delete(userAddress);
        log.info("地址删除成功：{}",delete);
    }

    @Transactional(propagation = Propagation.REQUIRED ,rollbackFor = SQLException.class)
    @Override
    public void setDefault(String userId, String addressId) {


        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        List<UserAddress> addresses = queryAddressByUserId(userId);
        for (UserAddress address : addresses) {
            address.setIsDefault(YesOrNo.NO.type);
            Example example = new Example(UserAddress.class);
            example.createCriteria().andEqualTo("id",address.getId());
            userAddressMapper.updateByExampleSelective(address,example);
        }

        Example example = new Example(UserAddress.class);
        example.createCriteria().andEqualTo("id",addressId);
        userAddress.setIsDefault(YesOrNo.YES.type);

        int i = userAddressMapper.updateByExampleSelective(userAddress, example);
        log.info("设置默认：{}",i);
    }



}
