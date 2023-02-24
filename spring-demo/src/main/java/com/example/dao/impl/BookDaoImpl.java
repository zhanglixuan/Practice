package com.example.dao.impl;

import com.example.dao.BookDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
@Repository("bookDao")
@Scope("singleton")
public class BookDaoImpl implements BookDao {

    @Value("${jdbc.username}")
    private String name;

    public void save() {
        System.out.println("book com.example.dao save"+name);
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
