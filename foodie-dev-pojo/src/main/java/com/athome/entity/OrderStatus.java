package com.athome.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单状态表
 * </p>
 *
 * @author Zengfc
 * @since 2021-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID 对应订单表的主键id
     */
    private String orderId;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单创建时间 对应[10:待付款]状态
     */
    private Date createdTime;

    /**
     * 支付成功时间 对应[20:已付款，待发货]状态
     */
    private Date payTime;

    /**
     * 发货时间 对应[30：已发货，待收货]状态
     */
    private Date deliverTime;

    /**
     * 交易成功时间 对应[40：交易成功]状态
     */
    private Date successTime;

    /**
     * 交易关闭时间 对应[50：交易关闭]状态
     */
    private Date closeTime;

    /**
     * 留言时间 用户在交易成功后的留言时间
     */
    private Date commentTime;


}
