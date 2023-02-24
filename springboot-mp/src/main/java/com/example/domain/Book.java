package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 张丽璇
 * @date 2023/2/22
 */
@Data
@TableName("tbl_book")
public class Book {
	private Long id;
	private String name;
	private String type;
	private String description;
}
