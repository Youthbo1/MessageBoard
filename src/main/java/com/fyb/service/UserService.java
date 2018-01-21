package com.fyb.service;

import com.fyb.bean.User;
import com.fyb.dao.UserDAO;

/**
 * \Date: 2018/1/21
 * \
 * \Description:
 * \
 */
public class UserService {
    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 成功返回用户Bean，失败返回null
     */
    public User login(String username, String password) {
        return userDAO.login(username, password);
    }


}
