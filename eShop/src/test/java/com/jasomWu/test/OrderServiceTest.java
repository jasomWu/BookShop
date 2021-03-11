package com.jasomWu.test;

import com.jasomWu.pojo.Cart;
import com.jasomWu.pojo.CartItem;
import com.jasomWu.pojo.Order;
import com.jasomWu.service.OrderService;
import com.jasomWu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sunwu
 * @create 2021-02-06-23:32
 */
public class OrderServiceTest {
    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {

        Cart cart = new Cart();
        cart.addCart(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCart(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCart(new CartItem(2,"C",1,new BigDecimal(2000),new BigDecimal(2000)));
        System.out.println(orderService.createOrder(cart, 1));
    }
    @Test
    public void showMyOrder(){
        List<Order> orders = orderService.showMyOrder(1);
        orders.forEach(System.out::println);
    }
    @Test
    public void showAllOrder(){
        List<Order> orders = orderService.showAllOrders();
        orders.forEach(System.out::println);
    }

}