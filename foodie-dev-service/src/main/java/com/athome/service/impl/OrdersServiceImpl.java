package com.athome.service.impl;


import com.athome.bo.SubmitOrderBO;
import com.athome.entity.*;
import com.athome.enums.OrderStatusEnum;
import com.athome.enums.YesOrNo;
import com.athome.mapper.OrderItemsMapper;
import com.athome.mapper.OrderStatusMapper;
import com.athome.mapper.OrdersMapper;
import com.athome.mapper.UserAddressMapper;
import com.athome.service.*;
import io.swagger.models.auth.In;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
@Service
public class OrdersServiceImpl  implements IOrdersService {

    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    IUserAddressService userAddressService;
    @Autowired
    IItemsSpecService itemsSpecService;
    @Autowired
    IItemsService itemsService;
    @Autowired
    IItemsImgService itemsImgService;
    @Autowired
    OrderItemsMapper orderItemsMapper;
    @Autowired
    OrderStatusMapper orderStatusMapper;

    @Autowired
    Sid sid;


    @Override
    public void createOrders(SubmitOrderBO submitOrderBO) {
        // 1. 新订单数据保存
        String orderId = sid.nextShort();
        UserAddress userAddress = userAddressService.queryAddressByUserIdAndId(submitOrderBO.getUserId(), submitOrderBO.getAddressId());

        Orders orders = new Orders();
        orders.setId(orderId);
        orders.setUserId(userAddress.getUserId());
        orders.setReceiverName(userAddress.getReceiver());
        orders.setReceiverMobile(userAddress.getMobile());
        orders.setReceiverAddress(userAddress.getProvince() + " "
                + userAddress.getCity() +" "
                + userAddress.getDistrict() + " "
                + userAddress.getDetail());

        //orders.setTotalAmount();
        //orders.setRealPayAmount();
        orders.setPostAmount(0);
        orders.setPayMethod(submitOrderBO.getPayMethod());
        orders.setLeftMsg(submitOrderBO.getLeftMsg());
        orders.setIsComment(YesOrNo.NO.type);
        orders.setIsDelete(YesOrNo.NO.type);
        orders.setCreatedTime(new Date());
        orders.setUpdatedTime(new Date());

        // 2. 循环根据itemSpecIds保存订单商品信息表

        String[] split = submitOrderBO.getItemSpecIds().split(",");
                //商品原价累计
        Integer totalAmount = 0;
                //优惠后的实际支付价格累计
        Integer realPayAmount = 0;

        for (String itemSpecId : split) {
            // TODO 整合redis后，商品购买的数量重新从redis的购物车中获取
            int buyCount = 1 ;
            // 2.1 根据规格id，查询具体规格信息，主要获取价格
            ItemsSpec itemsSpec = itemsSpecService.queryItemSpecById(itemSpecId);
            totalAmount += itemsSpec.getPriceNormal() * buyCount;
            realPayAmount += itemsSpec.getPriceDiscount() * buyCount;

            // 2.2 根据规格id，获取商品信息以及商品图片
            Items items = itemsService.queryItemById(itemsSpec.getItemId());
            String imgUrl = itemsImgService.queryItemMainByItemId(itemsSpec.getItemId());

            // 2.3 循环保存子订单数据到数据库
            OrderItems orderItems = new OrderItems();
            orderItems.setId(sid.nextShort());
            orderItems.setOrderId(orderId);
            orderItems.setItemId(items.getId());
            orderItems.setItemName(items.getItemName());
            orderItems.setItemImg(imgUrl);
            orderItems.setBuyCounts(buyCount);
            orderItems.setItemSpecId(itemsSpec.getId());
            orderItems.setItemSpecName(itemsSpec.getName());
            orderItems.setPrice(itemsSpec.getPriceDiscount());
            orderItemsMapper.insert(orderItems);

            // 2.4 用户提交订单后，自动扣除库存
            itemsSpecService.decreaseItemSpecStock(itemSpecId,buyCount);
        }

        orders.setTotalAmount(totalAmount);
        orders.setRealPayAmount(realPayAmount);
        ordersMapper.insert(orders);

        // 3. 保存订单状态表
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        orderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(orderStatus);


    }
}
