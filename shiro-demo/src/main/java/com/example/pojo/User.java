package com.example.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 张丽璇
 * @date 2023/2/28
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {
	private static final long serialVersionUID = -3291534702455752121L;

	@TableId(type = IdType.AUTO)
	private Integer id;
	private String username;
	private String realName;
	private String password;
	private String privateSalt; //随机盐，用于加密
	@TableLogic(value = "0", delval = "1")
	@TableField(fill = FieldFill.INSERT)
	private Integer status;
	@Version
	@TableField(fill = FieldFill.INSERT)
	private Integer version;

}
