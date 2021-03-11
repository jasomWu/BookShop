package com.jasomWu.dao.impl;

import com.jasomWu.dao.BaseDao;
import com.jasomWu.dao.OrderItemDao;
import com.jasomWu.pojo.OrderItem;

/**
 * @author sunwu
 * @create 2021-02-06-23:11
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(), orderItem.getOrderId());
    }
}
