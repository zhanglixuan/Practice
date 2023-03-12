package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 张丽璇
 * @date 2023/3/6
 */
// @Mapper
public interface MenuMapper extends BaseMapper<Menu> {
	List<String> selectPermsByUserId(Long userid);
}
