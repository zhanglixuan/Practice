package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 张丽璇
 * @date 2023/2/24
 */
@Mapper
public interface AccountDao extends BaseMapper<Account> {
}
