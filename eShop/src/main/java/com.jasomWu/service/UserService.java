package com.jasomWu.service;

import com.jasomWu.pojo.User;

/**
 * @author sunwu
 * @create 2021-02-01-22:03
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 返回null表示登录失败
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，false表示不存在
     */
    public boolean existsUsername(String username);

}
