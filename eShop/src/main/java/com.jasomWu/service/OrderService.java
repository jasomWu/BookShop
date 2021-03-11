package com.jasomWu.service;

import com.jasomWu.pojo.Cart;
import com.jasomWu.pojo.Order;

import java.util.List;

/**
 * @author sunwu
 * @create 2021-02-06-23:17
 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId);

    public List<Order> showMyOrder(Integer userId);

    public List<Order> showAllOrders();
}
