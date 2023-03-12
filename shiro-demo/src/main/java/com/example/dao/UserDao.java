package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.Set;

/**
 * @author 张丽璇
 * @date 2023/2/28
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
	@Select("select b.code from user a,role b,user_role c where a.id=c.User and b.id=c.Role and a.username=#{username}")
	Set<String> getRoleByName(String username);
}
