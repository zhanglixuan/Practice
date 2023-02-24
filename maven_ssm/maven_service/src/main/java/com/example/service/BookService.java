package com.example.service;

import com.example.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张丽璇
 * @date 2023/2/22
 */
@Transactional
public interface BookService {
	/**
	 * 保存
	 */
	boolean save(Book book);

	/**
	 * 更新
	 */
	boolean update(Book book);

	/**
	 * 按id删除
	 */
	boolean delete(Integer id);

	/**
	 * 查询所有
	 */
	List<Book> getAll();

	/**
	 * 根据id查询
	 */
	Book getById(Integer id);
}
