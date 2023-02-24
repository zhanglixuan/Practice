package com.example.service.util;

import com.example.dao.BookDao;
import com.example.dao.impl.BookDaoImpl;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
public class BookDaoFactory implements FactoryBean<BookDao> {
	public static BookDao getInstance(){
		return new BookDaoImpl();
	}

	public BookDao getBookDaoInstance(){
		return new BookDaoImpl();
	}

	public BookDao getObject() throws Exception {
		return new BookDaoImpl();
	}

	public Class<?> getObjectType() {
		return BookDaoImpl.class;
	}
}
