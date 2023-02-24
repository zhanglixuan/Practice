package com.example.service.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileReader;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author 张丽璇
 * @date 2023/2/15
 */
public class DruidDemo {
    @Test
    public void druid() throws Exception {
//        System.out.println(System.getProperty("user.dir"));
        //加载配置文件
        Properties properties = new Properties();
        properties.load(new FileReader("src/druid.properties"));
        //获取线程池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        //获取数据库连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
