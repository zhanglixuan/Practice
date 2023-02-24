package com.example.service;

import com.example.dao.BookDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
public class App {
    public static void main(String[] args) {
        // 3.加载配置文件得到上下文对象，也就是IOC容器对象
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //4、获取bean对象
       ctx.getBean(BookDao.class).save();
    }
}
