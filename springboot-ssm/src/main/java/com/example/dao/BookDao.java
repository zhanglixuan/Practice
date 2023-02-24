package com.example.dao;

import com.example.domain.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 张丽璇
 * @date 2023/2/22
 */
@Mapper
public interface BookDao {
	@Insert("INSERT INTO tbl_book(type,name,description) VALUES(#{id},#{name},#{description})")
	boolean save(Book book);

	@Update("UPDATE tbl_book SET type=#{type},name=#{name},description=#{description}")
	boolean update(Book book);

	@Delete("DELETE tbl_book WHERE id=#{id}")
	boolean delete(Integer id);

	@Select("SELECT * FROM tbl_book")
	List<Book> getAll();

	@Select("SELECT * FROM tbl_book WHERE id=#{id}")
	Book getById(Integer id);
}
