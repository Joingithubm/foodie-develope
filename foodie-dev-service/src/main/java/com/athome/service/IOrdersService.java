package com.athome.service;


import com.athome.bo.SubmitOrderBO;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
public interface IOrdersService{

    /**
     * 创建订单
     * @param submitOrderBO
     */
    void createOrders(SubmitOrderBO submitOrderBO);

}
