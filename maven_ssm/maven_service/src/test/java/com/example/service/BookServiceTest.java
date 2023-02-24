package com.example.service;

import com.example.config.SpringConfigDao;
import com.example.config.SpringConfigService;
import com.example.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
* @author 张丽璇
* @date 2023/2/22
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfigService.class, SpringConfigDao.class})
public class BookServiceTest {
	@Autowired
	private BookService bookService;
	@Test
	public void testGetAll(){
		List<Book> books = bookService.getAll();
		System.out.println(books);
	}

	@Test
	public void testGetById(){
		Book book = bookService.getById(1);
		System.out.println(book);
	}
}
