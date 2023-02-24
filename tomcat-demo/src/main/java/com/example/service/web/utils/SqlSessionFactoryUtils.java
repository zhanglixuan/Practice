package com.example.service.web.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 张丽璇
 * @date 2023/2/19
 */
public class SqlSessionFactoryUtils {
    //将来获取SqlSession连接对象直接从SqlSessionFactory中拿，
    // 因为每一个SqlSessionFactory里面都绑定了一个数据库连接池，
    // 所以创建多次就意味着会有多个连接池，资源消耗太大，故指创建一次。
    private static SqlSessionFactory sqlSessionFactory;

    static {
        //读取mybatis核心配置文件并获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            //使用建造者模式创建一个对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static SqlSessionFactory getInstance(){
        return sqlSessionFactory;
    }
}
