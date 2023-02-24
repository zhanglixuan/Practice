package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 张丽璇
 * @date 2023/2/24
 */
@Data
@Accessors(chain = true)
public class Account {
	// @TableId(type = IdType.ASSIGN_ID)
	private Long id;
	private String account;
	private Double money;
	@TableField(exist = false) //表示在数据库中不存在该字段
	private Integer online;
	@TableLogic(value = "0", delval = "1")
	private Integer deleted;
	@Version  //乐观锁注解
	private Integer version;
}
