package com.jasomWu.web;

import com.google.gson.Gson;
import com.jasomWu.pojo.User;
import com.jasomWu.service.UserService;
import com.jasomWu.service.impl.UserServiceImpl;
import com.jasomWu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author sunwu
 * @create 2021-02-03-17:37
 */
public class UserServlet extends BaseServlet {

    //通过调用service层检查用户名是否正确
    private UserService service = new UserServiceImpl();


    /**
     * ajax异步请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取请求参数username
        String username = req.getParameter("username");
        //调用service.existsUsername()
        boolean existsUsername = service.existsUsername(username);
        //把返回的结果封装成map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);


    }

    /**
     *  注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //销毁session
        req.getSession().invalidate();;
        //请求重定向
        resp.sendRedirect(req.getContextPath());

    }

    /**
     *  登陆的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2.调用Service层的方法
        User login = service.login(new User(0, username, password, null));

        //根据login()的返回结果判断登录是否成功
        if (null==login){
            //不成功，跳回登录页面
            // System.out.println("用户名或密码错误！");
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            //成功，跳转到登录成功页面
            //利用session保存用户信息
            req.getSession().setAttribute("user",login);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }

    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
            //获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除验证码，防止重复提交表单
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

            //1.获取请求的参数
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //2.检查 验证码是否可用
            if (token!=null&&token.equalsIgnoreCase(code)){
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
