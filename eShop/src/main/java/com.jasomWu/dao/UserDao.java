package com.jasomWu.dao;

import com.jasomWu.pojo.User;

/**
 * @author sunwu
 * @create 2021-02-01-18:44
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User queryUserByName(String username);

    /**
     * 根据用户名和密码   查询用户信息
     * @param name
     * @param password
     * @return
     */
    public User queryUserByNameAndPassword(String name,String password);
    /**
     * 向数据库中插入User对象
     *
     * @param user
     */
    public int saveUser(User user);

}
