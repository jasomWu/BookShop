package com.jasomWu.dao.impl;

import com.jasomWu.dao.BaseDao;
import com.jasomWu.dao.UserDao;
import com.jasomWu.pojo.User;

/**
 * @author sunwu
 * @create 2021-02-01-19:24
 */
public class UserImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByName(String username) {
        String sql = "select `id`,`username`,`password`,`email`from t_user where username=?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByNameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email`from t_user where username=? and password=?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`)value(?,?,?)";
        return update(sql, user.getUsername(),user.getPassword(),user.getEmail());
    }
}
