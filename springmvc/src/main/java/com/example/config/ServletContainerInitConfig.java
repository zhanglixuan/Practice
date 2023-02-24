package com.example.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * @author 张丽璇
 * @date 2023/2/21
 */
//定义一个servlet容器启动的配置类，在里面加载Spring的配置
	//如果都配置了，那么服务器启动之后，就会有springmvc容器以及spring容器
//这种写法是springmvc专用的
// public class ServletContainerInitConfig extends AbstractDispatcherServletInitializer {
// 	//加载Springmvc容器的配置的
// 	protected WebApplicationContext createServletApplicationContext() {
// 		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
// 		ctx.register(SpringMvcConfig.class);
// 		return ctx;
// 	}
//
// 	//设置哪些请求归属springmvc处理
// 	protected String[] getServletMappings() {
// 		return new String[]{"/"}; //所有请求
// 	}
//
// 	//加载spring容器配置
// 	protected WebApplicationContext createRootApplicationContext() {
// 		return null;
// 	}
// }
//简化开发格式
public class ServletContainerInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
	protected Class<?>[] getRootConfigClasses() {
		return new Class[0];
	}

	//加载springmvc配置文件，创建springmvc容器
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{SpringMvcConfig.class};
	}

	//拦截所有请求，交由springmvc处理
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	//配字符编码过滤器 解决post请求中文乱码问题
	//作用：对资源的请求进行拦截，进行增强处理之后，放行
	@Override
	protected Filter[] getServletFilters() {
		//tomcat默认编码解码字符集o8859-1，编码解码使用字符集不一致导致中文乱码
		//post请求servlet是通过IO流对请求头数据进行读写的。
		//直接设置编码方式就可解决
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("utf-8");
		return new Filter[]{filter};
	}
}
