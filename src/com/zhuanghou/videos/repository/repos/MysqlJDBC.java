package com.zhuanghou.videos.repository.repos;

/**
 * Created by duhui on 2017/11/24.
 */

import java.sql.*;
import java.text.DateFormat;

public class MysqlJDBC {
    //声明Connection对象
    public static Connection con = null;
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


    static {
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            statement = con.createStatement();


        } catch (Exception e) {
            //数据库驱动类异常处理
            e.printStackTrace();
        }
    }


    public static void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        String sql = "select * from admin";
//        String[] s = {};
//        MysqlJDBC.select(sql, s);

        String sql="insert into admin values(null,?,?,'2017-11-24 17:34:42')";
        String[] strings={"admin","123"};
        String insert="insert into user values(null, '小白','123456789',now())";
        String[] strings1={};
        MysqlJDBC.cud(insert,strings1);
        //遍历查询结果集
    }

    public static ResultSet select(String sql, String[] s) {
        //要执行的SQL语句
        PreparedStatement psql = null;
        try {
            psql = con.prepareStatement(sql);
            for (int i = 1; i <= s.length; i++) {
                psql.setString(i, s[i - 1]);
            }
            rs = psql.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {


        }

        return rs;
    }

    public static boolean cud(String sql, String[] s) {
        boolean b=true;
        //要执行的SQL语句
        PreparedStatement psql = null;
        try {
            psql = con.prepareStatement(sql);
            for (int i = 1; i <= s.length; i++) {
                psql.setString(i, s[i - 1]);
            }
             psql.executeUpdate();
        } catch (Exception e) {
            b=false;
            e.printStackTrace();
        } finally {

//            close();
        }
        return b;
    }

}