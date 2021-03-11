package com.jasomWu.service.impl;

import com.jasomWu.dao.UserDao;
import com.jasomWu.dao.impl.UserImpl;
import com.jasomWu.pojo.User;
import com.jasomWu.service.UserService;

/**
 * @author sunwu
 * @create 2021-02-01-22:14
 */
public class UserServiceImpl implements UserService {

    private UserDao dao = new UserImpl();

    @Override
    public void registUser(User user) {
        dao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return dao.queryUserByNameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        //等于null表示没有查到，返回false说明用户名可用
        if (dao.queryUserByName(username)==null){
            return false;
        }else {
            return true;
        }
    }
}
