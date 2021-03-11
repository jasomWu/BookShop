package com.jasomWu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author sunwu
 * @create 2021-02-01-14:53
 */
public class JDBCUtils {

    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    private static DruidDataSource source;
    static {
        try {
            Properties pro = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pro.load(is);
            source = (DruidDataSource) DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection()  {

        Connection conn = conns.get();

        if (conn==null){
            try {
                conn = source.getConnection();//获取数据库连接池连接
                conns.set(conn);//保存到threadLoad对象中，供后面的jdbc操作
                conn.setAutoCommit(false);//设置为手动管理事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }

        return conn;
    }


    /**
     * 提交事务，关闭连接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if (connection!=null){
            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        //tomcat底层使用了连接池技术
        conns.remove();
    }

    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if (connection!=null){
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        //tomcat底层使用了连接池技术
        conns.remove();
    }

//    /**
//     * 关闭数据库连接
//     */
//    public static void closeConnection(Connection conn){
//        if (conn != null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }

}
