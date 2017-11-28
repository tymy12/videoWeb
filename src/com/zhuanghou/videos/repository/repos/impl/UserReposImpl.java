package com.zhuanghou.videos.repository.repos.impl;

import com.mysql.jdbc.ResultSet;
import com.zhuanghou.videos.repository.domain.User;
import com.zhuanghou.videos.repository.repos.MysqlJDBC;
import com.zhuanghou.videos.repository.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duhui on 2017/11/25.
 */
@Repository
public class UserReposImpl implements UserRepos {
    ResultSet rs;

    @Autowired
    private static UserRepos userRepos;

    @Override
    public List<User> selectUser(int page, int pageUsers) {
        List<User> users=new ArrayList();
        String sql="select *from user limit ?,?";
        String[] strings={((page-1)*pageUsers)+"",pageUsers+""};

        rs= (ResultSet) MysqlJDBC.select(sql,strings);
        try {
            while (rs.next()){
                User user=new User();
                user.setUsername(rs.getString(2));
                user.setMobile(rs.getString(3));
                user.setDate(rs.getString(4));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;

    }

    @Override
    public int getPageNum(int pageLists) {
        String sql="select count(*) from user";
        String[] strings={};
        rs= (ResultSet) MysqlJDBC.select(sql,strings);
        int pageNum = 0;
        try {
            rs.next();
            pageNum=Integer.valueOf(rs.getString(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageNum;
    }

    @Override
    public boolean deleteUser(String mobile) {
        String sql="delete from user where mobile=?";
        String[] strings={mobile};

        return MysqlJDBC.cud(sql,strings);
    }

    @Override
    public boolean insertUser(String name, String mobile) {
        String sql="insert into   user values(null, ?,?,now())";
        String[] strings={name,mobile};
        return MysqlJDBC.cud(sql,strings);
    }

    @Override
    public boolean updateUser(String name, String mobile, String usedmobile) {
        String sql="update user set username=?,mobile=? where mobile=?";

        String[] strings={name,mobile,usedmobile};
        return MysqlJDBC.cud(sql,strings);
    }

    @Override
    public List<User> queryBy(String mobile) {
        List<User> users=new ArrayList();


        String sql = "select*from user where mobile=?";
        String[] strings = {mobile};
        ResultSet rs = (ResultSet) MysqlJDBC.select(sql, strings);
        try {
        while (rs.next()) {
            User user=new User();
            user.setUsername(rs.getString(2));
            user.setMobile(rs.getString(3));
            user.setDate(rs.getString(4));
            users.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return users;
    }

    @Override
    public List<User> queryuser(String key) {
        List<User> users=new ArrayList();


        String sql = "select*from user where mobile=? or username=?";
        String[] strings = {key,key};
        ResultSet rs = (ResultSet) MysqlJDBC.select(sql, strings);
        try {
            while (rs.next()) {
                User user=new User();
                user.setUsername(rs.getString(2));
                user.setMobile(rs.getString(3));
                user.setDate(rs.getString(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;    }


}
