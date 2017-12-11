package com.zhuanghou.videos.repository.repos.impl;

import com.mchange.v2.c3p0.impl.NewProxyResultSet;
import com.mysql.jdbc.ResultSet;
import com.zhuanghou.videos.repository.domain.User;
import com.zhuanghou.videos.repository.repos.JdbcUtils;
import com.zhuanghou.videos.repository.repos.MysqlJDBC;
import com.zhuanghou.videos.repository.repos.UserRepos;
import com.zhuanghou.videos.tool.MyTime;
import org.apache.commons.dbcp.DelegatingResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by duhui on 2017/11/25.
 */
@Repository
public class UserReposImpl implements UserRepos {
    private static ExecutorService ThreadPool = Executors.newFixedThreadPool(30);
    private static User user;

    public static void main(String[] str) {

        UserReposImpl repos = new UserReposImpl();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        final int imax = 0;
        for (int i = 0; i < imax; i++) {
            final int finalI = i;
            ThreadPool.execute(new Runnable() {


                @Override
                public void run() {

                }
            });

        }

        for (int i = 12014; i < 100000; i++) {
            String sql = "insert into user values(null, ?,?,now())";
            String[] strings = {"小华" + i, "666666" + i};
            MysqlJDBC.c3p0cud(sql, strings);
        }

//        for(int i=2013;i<12013;i++){
//            String sql="insert into video values(null, ?,?,'《美国往事》是一部由瑟吉欧·莱昂执导，罗伯特·德尼罗、詹姆斯·伍兹、伊丽莎白·麦戈文、塔斯黛·韦尔德等人主演的剧情片。影片以纽约的犹太社区为背景，讲述了主人公“面条”从懵懂少年成长为黑帮大佬的历程，同时也展现了美国从20世纪20年代到60年代的黑帮史。1984年2月，该片在美国上映。1985年，该片获得了第8 届日本电影学院奖最佳外语片等奖项。',now())";
//            String[] strings={"美国往事"+i,"/stat/vdreo/3dscd.mp4"+i};
//            MysqlJDBC.c3p0cud(sql,strings);
//        }

        System.out.println(formatter.format(new Date()));


    }


    @Autowired
    private static UserRepos userRepos;

    @Override
    public List<User> selectUser(int page, int pageUsers) {
        List<User> users = new ArrayList();
        String sql = "select *from user limit ?,?";
        String[] strings = {((page - 1) * pageUsers) + "", pageUsers + ""};

        DelegatingResultSet newProxyResultSet = null;

        Object[] objects = MysqlJDBC.select(sql, strings);
        newProxyResultSet = (DelegatingResultSet) objects[0];

        try {

            while (newProxyResultSet.next()) {
                User user = new User();
                user.setUsername(newProxyResultSet.getString(2));
                user.setMobile(newProxyResultSet.getString(3));
                user.setDate(newProxyResultSet.getString(4));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MysqlJDBC.close(objects);
        }


        return users;

    }


    @Override
    public int getPageNum(int pageLists) {
        String sql = "select count(*) from user";
        String[] strings = {};
//        rs = (NewProxyResultSet) MysqlJDBC.select(sql, strings);
        DelegatingResultSet newProxyResultSet = null;
        Object[] objects = MysqlJDBC.select(sql, strings);
        newProxyResultSet = (DelegatingResultSet) objects[0];
        int pageNum = 0;
        try {
            newProxyResultSet.next();
            pageNum = Integer.valueOf(newProxyResultSet.getString(1));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MysqlJDBC.close(objects);
        }
        return pageNum;
    }

    @Override
    public boolean deleteUser(String mobile) {
        String sql = "delete from user where mobile=?";
        String[] strings = {mobile};

        return MysqlJDBC.cud(sql, strings);
    }

    @Override
    public boolean insertUser(String name, String mobile) {
        String sql = "insert into   user values(null, ?,?,now())";
        String[] strings = {name, mobile};
        return MysqlJDBC.cud(sql, strings);
    }

    @Override
    public boolean updateUser(String name, String mobile, String usedmobile) {
        String sql = "update user set username=?,mobile=? where mobile=?";

        String[] strings = {name, mobile, usedmobile};
        return MysqlJDBC.cud(sql, strings);
    }

    @Override
    public List<User> queryBy(String mobile) {

        List<User> users = new ArrayList();


        String sql = "select*from user where mobile=?";
        String[] strings = {mobile};
        DelegatingResultSet newProxyResultSet = null;

        Object[] objects = MysqlJDBC.select(sql, strings);
        newProxyResultSet = (DelegatingResultSet) objects[0];

        try {

            while (newProxyResultSet.next()) {

                User user = new User();
                user.setUsername(newProxyResultSet.getString(2));
                user.setMobile(newProxyResultSet.getString(3));
                user.setDate(newProxyResultSet.getString(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlJDBC.close(objects);
        }
        return users;
    }

    @Override
    public List<User> queryuser(String key) {
        long beginTime = System.currentTimeMillis();
        List<User> users = new ArrayList();


        String sql = "select*from user where mobile=? or username=?";
        String[] strings = {key, key};


//        rs = (NewProxyResultSet) MysqlJDBC.select(sql, strings);
        DelegatingResultSet newProxyResultSet = null;

        Object[] objects = MysqlJDBC.select(sql, strings);

        newProxyResultSet = (DelegatingResultSet) objects[0];

        try {
            while (newProxyResultSet.next()) {
                User user = new User();
                user.setUsername(newProxyResultSet.getString(2));
                user.setMobile(newProxyResultSet.getString(3));
                user.setDate(newProxyResultSet.getString(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlJDBC.close(objects);
        }

        System.out.println("结束查询：" + (System.currentTimeMillis() - beginTime));

        return users;
    }


    public List<User> ceshi(String key) {
        List<User> users = new ArrayList();
        NewProxyResultSet query = null;
        Connection connection = null;
        String sql = "select*from user where mobile=? or username=?";
        String[] strings = {key, key};

        DelegatingResultSet resultSet = null;

        Object[] objects = MysqlJDBC.select(sql, strings);
        resultSet = (DelegatingResultSet) objects[0];

        try {
//            connection = JdbcUtils.getConnection();
//
//            PreparedStatement psql = connection.prepareStatement(sql);
//            for (int i = 1; i <= strings.length; i++) {
//                psql.setString(i, strings[i - 1]);
//            }
//             query = (NewProxyResultSet) psql.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString(2));
                user.setMobile(resultSet.getString(3));
                user.setDate(resultSet.getString(4));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            MysqlJDBC.close(objects);
        }
        return users;
    }


}
