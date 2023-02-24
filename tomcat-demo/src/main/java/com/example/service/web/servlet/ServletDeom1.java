package com.example.service.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * @author 张丽璇
 * @date 2023/2/18
 */
//配置该资源的访问路径
@WebServlet("/demo1")
public class ServletDeom1 implements Servlet {
    /**
     * 调用时机：默认情况下，Servlet第一次被访问时，调用
     *         * loadOnStartup:
     *              0或整数:服务器启动时对象创建之后自动调用
     * 调用次数：1
     */
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init...");
    }

    public ServletConfig getServletConfig() {
        return null;
    }
    /**
     * 当servlet被访问时该方法会自动被执行
     */

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("hello servlet~");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
