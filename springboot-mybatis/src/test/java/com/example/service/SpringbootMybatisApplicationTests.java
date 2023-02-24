package com.example.service;

import com.example.dao.BookDao;
import com.example.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMybatisApplicationTests {
	@Autowired
	private BookDao bookDao;

	@Test
	void getById() {
		Book book = bookDao.getById(1);
		System.out.println(book);
	}

}
