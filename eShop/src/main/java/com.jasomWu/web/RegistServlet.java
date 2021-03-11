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
 *  注册与登录的servlet已合并到了UserServlet
 * @author sunwu
 * @create 2021-02-01-22:41
 */
public class RegistServlet extends HttpServlet {
    //通过调用service层检查用户名是否正确
    private UserService service = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //2.检查 验证码是否可用  ==写死，要求abcde
        if ("abcde".equalsIgnoreCase(code)){
            //正确 3.检查用户名是否可用
            boolean existsUsername = service.existsUsername(username);
            if (existsUsername){

                req.setAttribute("msg","用户名已存在！！");
                req.setAttribute("username",username);
                req.setAttribute("email",email);

                //不可用，调回注册页面
                System.out.println("用户名"+username+"已存在！");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //可用，调用Service保存到数据库
                service.registUser(new User(0,username,password,email));
                //跳到注册成功页面 registe_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            req.setAttribute("msg","验证码错误！！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            //不正确
            //跳回注册页面
            System.out.println("验证码"+code+"错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

        super.doPost(req, resp);
    }
}
