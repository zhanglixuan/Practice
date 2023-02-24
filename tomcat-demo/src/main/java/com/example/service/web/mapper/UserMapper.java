package com.example.service.web.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author 张丽璇
 * @date 2023/2/19
 */
public interface UserMapper {
    int selectByCondition(@Param("username") String username, @Param("password") String password);
}
