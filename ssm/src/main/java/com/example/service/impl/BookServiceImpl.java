package com.example.service.impl;

import com.example.dao.BookDao;
import com.example.domain.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张丽璇
 * @date 2023/2/22
 */
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;

	@Override
	public boolean save(Book book) {
		return bookDao.save(book);
	}

	@Override
	public boolean update(Book book) {
		return bookDao.update(book);
	}

	@Override
	public boolean delete(Integer id) {
		return bookDao.delete(id);
	}

	@Override
	public List<Book> getAll() {
		return bookDao.getAll();
	}

	@Override
	public Book getById(Integer id) {
		return bookDao.getById(id);
	}
}
