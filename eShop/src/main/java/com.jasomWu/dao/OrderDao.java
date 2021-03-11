package com.jasomWu.dao;

import com.jasomWu.pojo.Order;
import com.jasomWu.pojo.User;

import java.util.List;

/**
 * @author sunwu
 * @create 2021-02-06-23:05
 */
public interface OrderDao {
    public int saveOrder(Order order);

    public List<Order> queryOrdersByUserId(Integer userId);

    public List<Order> queryOrders();
}
