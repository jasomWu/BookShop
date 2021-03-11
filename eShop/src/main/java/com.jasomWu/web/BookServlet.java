package com.jasomWu.web;

import com.jasomWu.pojo.Book;
import com.jasomWu.pojo.Page;
import com.jasomWu.service.BookService;
import com.jasomWu.service.impl.BookServiceImpl;
import com.jasomWu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author sunwu
 * @create 2021-02-03-22:50
 */
public class BookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //1.获取请求参数 pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用bookService.page(pageNo，pageSize)
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3.保存page对象到request域中
        req.setAttribute("page",page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        //1.获取请求参数，封装成JavaBean对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //2.调用BookService.add()保存图书
        bookService.addBook(book);
        //跳到图书列表,重定向
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数id
        String id = req.getParameter("id");
        //2.删除图书
        int anInt = Integer.parseInt(id);
        bookService.deleteBook(anInt);
        //3.重定向
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数，封装成book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2.调用service对象更新book信息
        bookService.updateBook(book);
        //3.重定向返回列表管理
        //地址：/工程名/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));



    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String id = req.getParameter("id");
        int anInt = Integer.parseInt(id);

        //2.通过bookService.queryBookById查询图书
        Book book = bookService.queryBookById(anInt);

        //3.保存图书到request域中
         req.setAttribute("book",book);

        //4.请求转发到 pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();

        //2.把所有数据保存到request域中
        req.setAttribute("books",books);

        //3.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
