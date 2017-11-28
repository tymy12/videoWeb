package com.zhuanghou.videos.repository.repos.impl;

import com.mysql.jdbc.ResultSet;
import com.zhuanghou.videos.repository.domain.Manager;
import com.zhuanghou.videos.repository.repos.ManagerRepos;
import com.zhuanghou.videos.repository.repos.MysqlJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by duhui on 2017/11/24.
 */

@Repository
public class MangerReposImpl implements ManagerRepos {

    ResultSet rs = null;
    @Autowired
     public static ManagerRepos mangerRepos;


    @Override
    public Manager queryBy(String username) {
        Manager manager = new Manager();
        String sql = "select*from admin where username= ? ";
        String[] strings = {username};
        ResultSet rs = (ResultSet) MysqlJDBC.select(sql, strings);
        try {
            while (rs.next()) {
                manager.setUsername(rs.getString(2));
                manager.setPassword(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }
}
