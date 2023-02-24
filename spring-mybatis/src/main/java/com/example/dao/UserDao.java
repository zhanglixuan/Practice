package com.example.dao;

import com.example.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
@Repository
public interface UserDao {
	@Select("SELECT * FROM tb_user")
	List<User> selectAll();

	@Select("SELECT * FROM tb_user WHERE id= #{id}")
	User selectById(Integer id);

	@Insert("INSERT INTO tb_user(null,username,password,gender,addr) VALUES(#{username},#{password},#{gender},#{addr})")
	Integer addUser(User user);

	@Update("UPDATE tb_user SET username=#{username} WHERE id=#{id}")
	Integer update(@Param("id") Integer id, @Param("username") String username);

	@Delete("DELETE FROM tb_user WHERE id=#{id}")
	Integer delete(Integer id);
}
