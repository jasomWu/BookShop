package com.jasomWu.web;

import com.jasomWu.pojo.Cart;
import com.jasomWu.pojo.Order;
import com.jasomWu.pojo.User;
import com.jasomWu.service.OrderService;
import com.jasomWu.service.impl.OrderServiceImpl;
import com.jasomWu.utils.JDBCUtils;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunwu
 * @create 2021-02-07-8:18
 */
public class OrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = loginUser.getId();
//        调用orderService.createOrder(Cart,Userid);生成订单
        String orderId = null;
        orderId = orderService.createOrder(cart, userId);

//        req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);

        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int id = user.getId();
        List<Order> orders = orderService.showMyOrder(id);
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Order> orders = orderService.showAllOrders();
        req.setAttribute("AllOrders",orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);

    }





    }
