package com.fyb.dao;

import com.fyb.bean.User;
import com.fyb.common.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * \Date: 2018/1/21
 * \
 * \Description:
 * \
 */
public class UserDAO {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 成功返回用户Bean，失败返回null
     */
    public User login(String username,String password){
        Connection coon= ConnectionUtil.getConnection();
        String sql="select * from user where username=? and password=?";
        PreparedStatement statement=null;
        ResultSet rs=null;
        User user=null;

        try {
            statement=coon.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            rs=statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRealName(rs.getString("real_name"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
            }


        } catch (SQLException e) {
            System.out.println("登录失败。");
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs, statement, coon);
        }
        return user;


    }
}

