package JDBCUtil;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 */
public class Util {

    // 设置静态 , 静态代码块最先执行只能获取到静态变量
    private static String url;
    private static String user;
    private static String password;

    /**
     * 读取配置文件 , 在类加载时进行读取
     */
    static {
        //读取配置文件可以使用properties类来获取
        Properties pro = new Properties();
        //这里需要配置文件的路径,可以选择手动输入 , 但是比较麻烦
        //java提供了CLassLoader -- 类加载器
        //这个类对象可以通过src下的类的字节码文件获取
        ClassLoader cl = Util.class.getClassLoader();
        //classloader对象中有方法可以获取指定src目录下的文件路径 , 不过是url对象
        URL res = cl.getResource("jdbc.properties");
        //url中有方法可以获取到字符串路径
        String path = res.getPath();
        try {
            //加载配置文件进pro对象
            pro.load(new FileReader(path));
            //将配置写入
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            //加载驱动
            Class.forName(pro.getProperty("driver"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return Connection 数据库连接对象
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Statement
     * DML|DDL 操作
     * 释放数据库对象资源
     * 及时减少资源占用
     */
    public static void closeSrc(Connection conn, Statement stmt){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Statement
     * DQL 操作
     * 释放数据库对象资源
     * 及时减少资源占用
     */
    public static void closeSrc(Connection conn, Statement stmt, ResultSet rs){
        closeSrc(conn, stmt);
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * PreparedStatement
     * DML|DDL 操作
     * 释放数据库对象资源
     * 及时减少资源占用
     */
    public static void closeSrc(Connection conn, PreparedStatement pstmt){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * PreparedStatement
     * DQL 操作
     * 释放数据库对象资源
     * 及时减少资源占用
     */
    public static void closeSrc(Connection conn, PreparedStatement pstmt, ResultSet rs){
        closeSrc(conn, pstmt);
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
