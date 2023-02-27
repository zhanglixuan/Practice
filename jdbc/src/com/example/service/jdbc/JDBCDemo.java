package com.example.service.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * @author 张丽璇
 * @date 2023/2/15
 */
public class JDBCDemo {
    @Test
    public void tetstStmt() throws Exception {
        //注册驱动（加载驱动）
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&serverTimezone=Asia/Shanghai&useTimezone=true";
        String user = "root";
        String password = "root";
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
//      //定义sql语句
        String sql = "update account set money = 4000 where id = 1";
        //获取执行SQL对象
        Statement statement = connection.createStatement();
        //执行sql语句
        int i = statement.executeUpdate(sql);
        if (i > 0){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
        statement.close();
        connection.close();
    }

    /**
     * 测试PreparedStatement
     */
    @Test
    public void testPrepStmt() throws Exception {
        //注册驱动（加载驱动）
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useServerPrepStmts=false&serverTimezone=Asia/Shanghai&useTimezone=true";
        String user = "root";
        String password = "mily5yue23ri.";
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        String username = "张三";
        String pwd = "' or '1' = '1";
        //定义sql语句
        String sql = "select * from tb_user where username=? and password=?";
        //预编译sql并执行SQL语句
        ResultSet resultSet;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,pwd);
        resultSet = preparedStatement.executeQuery();

        preparedStatement.setString(1,username);
        preparedStatement.setString(2,pwd);

        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
