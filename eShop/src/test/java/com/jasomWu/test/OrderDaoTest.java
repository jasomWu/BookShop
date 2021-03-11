package com.jasomWu.test;

import com.jasomWu.dao.OrderDao;
import com.jasomWu.dao.impl.OrderDaoImpl;
import com.jasomWu.pojo.Order;
import com.jasomWu.pojo.User;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author sunwu
 * @create 2021-02-06-23:39
 */
public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {

        orderDao.saveOrder(new Order("1234567891", new Date(), new BigDecimal(100), 0, 1));
    }

    @Test
    public void queryOrdersByUserId() {
        List<Order> orders = orderDao.queryOrdersByUserId(1);
        orders.forEach(System.out::println);
    }

    @Test
    public void queryOrders(){
        List<Order> orders = orderDao.queryOrders();
        orders.forEach(System.out::println);
    }
}