package com.example.service.dao.impl;

import com.example.dao.BookDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
@Repository("bookDao1")
@Scope("singleton")
public class BookDaoImpl2 implements BookDao {

    public void save() {
        System.out.println("book dao save 2");
    }

    @PostConstruct
    public void init(){
        System.out.println("init...");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy...");
    }
}
