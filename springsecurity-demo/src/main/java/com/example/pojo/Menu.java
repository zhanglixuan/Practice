package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 张丽璇
 * @date 2023/3/6
 * 菜单表
 */
@Data
@TableName("sys_menu")
public class Menu implements Serializable {
	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 菜单名
	 */
	private String menuName;
	/**
	 * 路由地址
	 */
	private String path;
	/**
	 * 组件路径
	 */
	private String component;
	/**
	 * 菜单状态（0显示，1隐藏）
	 */
	private String visible;
	/**
	 * 菜单状态（0正常，1停用）
	 */
	private String status;
	/**
	 * 权限标识
	 */
	private String perms;
	/**
	 * 菜单图标
	 */
	private String icon;
	/**
	 * 创建人的用户id
	 */
	private Long createBy;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新人
	 */
	private Long updateBy;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 删除标志（0未删除 1已删除）
	 */
	private Integer delFlag;
	/**
	 * 备注
	 */
	private String remark;
}
