package com.jasomWu.test;

import com.jasomWu.pojo.User;
import com.jasomWu.service.UserService;
import com.jasomWu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author sunwu
 * @create 2021-02-01-22:20
 */
public class UserServiceTest {
    private UserService service = new UserServiceImpl();

    @Test
    public void registUser() {
        service.registUser(new User(0,"asc21as","sacaa","asvegv@qq.com"));
    }

    @Test
    public void login() {
        User user = service.login(new User(0,"asc21as","132456","null"));
        System.out.println(user);
    }

    @Test
    public void existsUsername() {
        if(service.existsUsername("asc21aws")){
            System.out.println("用户名已存在！");
        }else {
            System.out.println("用户名可用！");
        }
    }
}