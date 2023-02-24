package com.example.service.mapper;

import com.example.domain.User;

import java.util.List;

/**
 * @author 张丽璇
 * @date 2023/2/16
 */
public interface UserMapper {
    List<User> selectAll();

    User selectById();
}
