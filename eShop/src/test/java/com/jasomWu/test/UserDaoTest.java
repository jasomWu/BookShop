package com.jasomWu.test;

import com.jasomWu.dao.UserDao;
import com.jasomWu.dao.impl.UserImpl;
import com.jasomWu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author sunwu
 * @create 2021-02-01-21:20
 */
public class UserDaoTest {
    UserDao dao = new UserImpl();
    @Test
    public void queryUserByName() {

        User admin = dao.queryUserByName("admin");
        System.out.println(admin);
    }

    @Test
    public void queryUserByNameAndPassword() {
        User user = dao.queryUserByNameAndPassword("admin", "admin");
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        int saveUser = dao.saveUser(new User(0, "张三", "bsv35h3b3b", "zhangsan132@qq.com"));
        System.out.println(saveUser);

    }
}