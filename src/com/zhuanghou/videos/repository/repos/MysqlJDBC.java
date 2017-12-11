package com.zhuanghou.videos.repository.repos;

/**
 * Created by duhui on 2017/11/24.
 */

import com.mchange.v2.c3p0.impl.NewProxyResultSet;
import com.zhuanghou.videos.repository.domain.User;
import com.zhuanghou.videos.tool.Redis;
import org.apache.commons.dbcp.DelegatingResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.text.DateFormat;

public class MysqlJDBC {
    //声明Connection对象
//    public static Connection con = null;
    //驱动程序名
    public static String driver = "com.mysql.jdbc.Driver";
    //URL指向要访问的数据库名mydata
    public static String url = "jdbc:mysql://localhost:3306/videoweb?characterEncoding=UTF-8";
    //MySQL配置时的用户名
    public static String user = "root";
    //MySQL配置时的密码
    public static String password = "123456";
    public static ResultSet rs = null;
    public static Statement statement = null;


//    static {
//        try {
//            //加载驱动程序
//            Class.forName(driver);
//            //1.getConnection()方法，连接MySQL数据库！！
//            con = DriverManager.getConnection(url, user, password);
//            if (!con.isClosed())
//                System.out.println("Succeeded connecting to the Database!");
//            //2.创建statement类对象，用来执行SQL语句！！
//            statement = con.createStatement();
//
//
//        } catch (Exception e) {
//            //数据库驱动类异常处理
//            e.printStackTrace();
//        }
//    }


    public static void close(Object[] objects) {
        DelegatingResultSet resultSet = (DelegatingResultSet) objects[0];
        Connection connection = (Connection) objects[1];
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

//    public static void main(String[] args) {
////        String sql = "select * from admin";
////        String[] s = {};
////        MysqlJDBC.select(sql, s);
//
//        String sql = "insert into admin values(null,?,?,'2017-11-24 17:34:42')";
//        String[] strings = {"admin", "123"};
//        String insert = "insert into user values(null, '小白','123456789',now())";
//        String[] strings1 = {};
//        MysqlJDBC.cud(insert, strings1);
//        //遍历查询结果集
//    }

    public static Object[] select(String sql, String[] s) {
        //要执行的SQL语句
        PreparedStatement psql = null;
        Connection con = null;
        try {

            //con=JdbcUtils.getConnection();
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/test");
            con = ds.getConnection();
            System.out.println("获取数据源");

            psql = con.prepareStatement(sql);
            for (int i = 1; i <= s.length; i++) {
                psql.setString(i, s[i - 1]);
            }

            rs = psql.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

//            if(con!=null){
//                try {
//                    con.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }

        Object[] objects = {rs, con};
        return objects;
    }

    public static boolean cud(String sql, String[] s) {
        boolean b = true;
        Connection con = null;
        //要执行的SQL语句
        PreparedStatement psql = null;


        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/test");
            con = ds.getConnection();
            //con=JdbcUtils.getConnection();
            psql = con.prepareStatement(sql);
            for (int i = 1; i <= s.length; i++) {
                psql.setString(i, s[i - 1]);
            }
            psql.executeUpdate();
        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return b;
    }


    public static boolean c3p0cud(String sql, String[] s) {
        boolean b = true;
        Connection con = null;
        //要执行的SQL语句
        PreparedStatement psql = null;


        try {

            con=JdbcUtils.getConnection();
            psql = con.prepareStatement(sql);
            for (int i = 1; i <= s.length; i++) {
                psql.setString(i, s[i - 1]);
            }
            psql.executeUpdate();
        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return b;
    }

    public static NewProxyResultSet c3p0select(String sql, String[] s) {
        NewProxyResultSet resultSet=null;
        boolean b = true;
        Connection con = null;
        //要执行的SQL语句
        PreparedStatement psql = null;


        try {

            con=JdbcUtils.getConnection();
            psql = con.prepareStatement(sql);
            for (int i = 1; i <= s.length; i++) {
                psql.setString(i, s[i - 1]);
            }
            resultSet= (NewProxyResultSet) psql.executeQuery();
        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                   // con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return resultSet;
    }


    public static void main(String[] str){
        NewProxyResultSet resultSet=null;
        String sql="select *from user where id= ?";
        String[] strings={"10000"};
        User user=new User();

        Redis redis=new Redis();

        long beginTime = System.currentTimeMillis();

        if(redis.hexists("10000", "username")){
            String username=redis.hget("10000", "username");
            user.setUsername(username);
            System.out.println("结束查询：" + (System.currentTimeMillis() - beginTime));

            System.out.println("有缓存");

        }else {
            System.out.println("无缓存");
            resultSet = (NewProxyResultSet) c3p0select(sql,strings);
            try {
                while (resultSet.next()) {
                    user.setId(resultSet.getString(1));
                    user.setUsername(resultSet.getString(2));
                    user.setDate(resultSet.getString(3));
                    redis.hset(user.getId(), "username", user.getUsername());

                    redis.expire(user.getId(), 1800);
                    System.out.println("结束查询：" + (System.currentTimeMillis() - beginTime));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.print(user.getUsername());

    }


}