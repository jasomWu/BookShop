package com.jasomWu.service.impl;

import com.jasomWu.dao.BookDao;
import com.jasomWu.dao.OrderDao;
import com.jasomWu.dao.OrderItemDao;
import com.jasomWu.dao.impl.BookDaoImpl;
import com.jasomWu.dao.impl.OrderDaoImpl;
import com.jasomWu.dao.impl.OrderItemDaoImpl;
import com.jasomWu.pojo.*;
import com.jasomWu.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author sunwu
 * @create 2021-02-06-23:18
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //创建订单，唯一
        String orderId = System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);

        //保存订单项
        for(Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            //获得每一个商品
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);
            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showMyOrder(Integer userId) {
        List<Order> orders = orderDao.queryOrdersByUserId(userId);
        return orders;

    }

    @Override
    public List<Order> showAllOrders() {
        List<Order> orders = orderDao.queryOrders();
        return orders;
    }
}
