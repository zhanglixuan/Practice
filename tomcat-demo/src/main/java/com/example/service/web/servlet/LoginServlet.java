package com.example.service.web.servlet;
/**
 * @author 张丽璇
 * @date 2023/2/18
 */

import com.example.service.web.mapper.UserMapper;
import com.example.service.web.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/demo4")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数username
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //获取sqlSession对象(进行增删改操作时事务默认是打开的，需要手动提交)
        SqlSession sqlSession = SqlSessionFactoryUtils.getInstance().openSession();
        //获取Mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int count = userMapper.selectByCondition(username, password);
        System.out.println(count);
        sqlSession.close();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (count>0){
            writer.write("登录成功");
        }else {
            writer.write("登录失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
