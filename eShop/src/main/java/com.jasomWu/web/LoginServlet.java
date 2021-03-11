package com.jasomWu.web;

import com.jasomWu.pojo.User;
import com.jasomWu.service.UserService;
import com.jasomWu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *     注册与登录的servlet已合并到了UserServlet，以下代码没用用到
 * @author sunwu
 * @create 2021-02-02-14:01
 */
public class LoginServlet extends HttpServlet {
  private   UserService service = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2.调用Service层的方法
        User login = service.login(new User(0, username, password, null));

        //根据login()的返回结果判断登录是否成功
        if (null==login){
            //不成功，跳回登录页面
//            System.out.println("用户名或密码错误！");
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            //成功，跳转到登录成功页面
//            System.out.println("登录成功！");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }



    }
}
