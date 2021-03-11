package com.jasomWu.web;

import com.google.gson.Gson;
import com.jasomWu.pojo.Book;
import com.jasomWu.pojo.Cart;
import com.jasomWu.pojo.CartItem;
import com.jasomWu.service.BookService;
import com.jasomWu.service.impl.BookServiceImpl;
import com.jasomWu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sunwu
 * @create 2021-02-05-21:18
 */
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();


    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数，商品编号，商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            //更新商品数量
            cart.updateCount(id,count);
            //重定向
            resp.sendRedirect(req.getHeader("Referer"));

        }
    }
    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            //清空购物车
            cart.clear();
            //重定向到原来的购物车页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    /**
     * 删除商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.deleteItem(id);
            //重定向，商品列表
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("加入成功");
        //获取请求编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //找到图书
        Book book = bookService.queryBookById(id);
        //放到cartItem中
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addCart(cartItem);

//        System.out.println(cart);
//        System.out.println("请求头Referer的值：" + req.getHeader("Referer"));

        //最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());
        //重定向，商品列表
        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("加入成功");
        //获取请求编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //找到图书
        Book book = bookService.queryBookById(id);
        //放到cartItem中
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addCart(cartItem);

//        System.out.println(cart);
//        System.out.println("请求头Referer的值：" + req.getHeader("Referer"));

        //最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        //返回购物车总的商品和添加的一个商品
        Map<String,Object> resultMap = new HashMap<>();

        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String resultMapJson = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJson);
    }
}
