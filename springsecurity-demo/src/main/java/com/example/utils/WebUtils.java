package com.example.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张丽璇
 * @date 2023/3/5
 */
public class WebUtils {

	/**
	 * 将字符串渲染到客户端 （往响应中写数据）
	 * @param response 渲染对象
	 * @param string 待渲染的字符串
	 * @return
	 */
	public static String renderString(HttpServletResponse response, String string){
		try {
			response.setStatus(200);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(string); //写入到响应体中
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
