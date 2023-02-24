package com.example.service.test;

import com.example.service.mapper.BrandMapper;
import com.example.domain.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张丽璇
 * @date 2023/2/16
 */
public class MybatisTest {
    @Test
    public void selectById() throws IOException {
        //接收参数
        int id = 1;
        //加载mybatis核心配置文件，读取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象并执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = mapper.selectById(id);
        System.out.println(brand);
        sqlSession.close();

    }

    @Test
    public void selectAll() throws IOException {
        //加载mybatis核心配置文件，读取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象并执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        System.out.println(brands);
        sqlSession.close();
    }

    @Test
    public void selectByCondition() throws IOException {
        //接收参数
        int status = 1;
        String companyName = "华为技术有限公司";
        String brandName = "华为";

        //处理参数
        companyName = "%"+companyName+"%";
        brandName = "%"+brandName+"%";

        //封装对象
//        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setBrandName(brandName);
//        brand.setCompanyName(companyName);

        Map map = new HashMap();
        map.put("status", null);
        map.put("companyName", companyName);
        map.put("brandName", brandName);

        //加载mybatis核心配置文件，读取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象并执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectByCondition(map);
        System.out.println(brands);
        sqlSession.close();

    }

    @Test
    public void selectByConditionSingle() throws IOException {
        //接收参数
        int status = 1;
        String companyName = "华为技术有限公司";
        String brandName = "华为";

        //处理参数
        companyName = "%"+companyName+"%";
        brandName = "%"+brandName+"%";

        //封装对象
                Brand brand = new Brand();
                brand.setStatus(status);
                brand.setBrandName(brandName);
                brand.setCompanyName(companyName);

        //加载mybatis核心配置文件，读取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象并执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectByConditionSingle(brand);
        System.out.println(brands);
        sqlSession.close();
    }

    @Test
    public void testAdd() throws IOException {
        //接收参数
        int status = 1;
        String companyName = "波导";
        String brandName = "波导手机";
        int order = 1;
        String description = "手机中的战斗机";

        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(order);
        brand.setDescription(description);

        //加载mybatis核心配置文件，读取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象并执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        Integer id = brand.getId();
        System.out.println(id);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testUpdate() throws IOException {
        //接收参数
        int status = 1;
        String companyName = "波导";
        String brandName = "波导手机";
        int order = 1;
        String description = "手机中的战斗机";
        int id = 4;

        //封装对象
        Brand brand = new Brand();
        brand.setId(id);
        brand.setStatus(status);
//        brand.setBrandName(brandName);
//        brand.setCompanyName(companyName);
//        brand.setOrdered(order);
//        brand.setDescription(description);

        //加载mybatis核心配置文件，读取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象并执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int count = mapper.update(brand);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteById() throws IOException {
        //接收参数
        int id = 4;
        //加载mybatis核心配置文件，读取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象并执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteByIds() throws IOException {
        //接收参数
        int[] ids = {5,7,8};
        //加载mybatis核心配置文件，读取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象并执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper的代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }
}
