package com.jasomWu.dao.impl;

import com.jasomWu.dao.BaseDao;
import com.jasomWu.dao.OrderDao;
import com.jasomWu.pojo.Order;
import com.jasomWu.pojo.User;

import java.util.List;

/**
 * @author sunwu
 * @create 2021-02-06-23:06
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id` )values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status` from t_order where user_id=?";
        return queryForList(Order.class,sql,userId);
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` from t_order";
        return queryForList(Order.class,sql);
    }
}
