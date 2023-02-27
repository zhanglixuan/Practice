package com.example.service;

import com.example.SpringbootSsmApplication;
import com.example.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
* @author 张丽璇
* @date 2023/2/22
*/
@SpringBootTest(classes = SpringbootSsmApplication.class)
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
