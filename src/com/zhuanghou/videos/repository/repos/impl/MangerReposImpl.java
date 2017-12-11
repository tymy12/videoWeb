package com.zhuanghou.videos.repository.repos.impl;

import com.mchange.v2.c3p0.impl.NewProxyResultSet;
import com.mysql.jdbc.ResultSet;
import com.zhuanghou.videos.repository.domain.Manager;
import com.zhuanghou.videos.repository.repos.ManagerRepos;
import com.zhuanghou.videos.repository.repos.MysqlJDBC;
import org.apache.commons.dbcp.DelegatingResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by duhui on 2017/11/24.
 */

@Repository
public class MangerReposImpl implements ManagerRepos {

    @Autowired
     public static ManagerRepos mangerRepos;


    @Override
    public Manager queryBy(String username) {
        Manager manager = new Manager();
        String sql = "select*from admin where username= ? ";
        String[] strings = {username};
        DelegatingResultSet newProxyResultSet =null;

        Object[] objects=MysqlJDBC.select(sql,strings);
        newProxyResultSet = (DelegatingResultSet) objects[0];
        try {
            while (newProxyResultSet.next()) {
                manager.setId(newProxyResultSet.getString(1));
                manager.setUsername(newProxyResultSet.getString(2));
                manager.setPassword(newProxyResultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MysqlJDBC.close(objects);
        }
        return manager;
    }
}
