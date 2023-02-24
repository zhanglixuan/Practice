package com.example.service.impl;

import com.example.dao.BookDao;
import com.example.service.BookService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
@Service("bookService")
public class BookServiceImpl implements BookService, InitializingBean, DisposableBean {
    //5.删除service中new的dao对象
    @Autowired
    @Qualifier("bookDao") //指定装配bean的id或名称
    BookDao bookDao; //使用暴力反射对私有属性进行初始化
    public void save() {
        System.out.println("book com.example.service save");
        bookDao.save();
    }

    //6.对外提供传入dao对象的方法
    public void setBookDao(BookDao bookDao) {
        System.out.println("set...");
        this.bookDao = bookDao;
    }

    public void init(){
        System.out.println("初始化...");
    }
    public void destroy(){
        System.out.println("销毁");
    }

    public void afterPropertiesSet() throws Exception {
        init();
    }
}
