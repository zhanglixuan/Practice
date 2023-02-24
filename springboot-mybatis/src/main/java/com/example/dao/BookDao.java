package com.example.dao;

import com.example.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 张丽璇
 * @date 2023/2/24
 */
@Mapper
public interface BookDao {
	@Select("SELECT * FROM tbl_book WHERE id=#{id}")
	public Book getById(Integer id);
}
