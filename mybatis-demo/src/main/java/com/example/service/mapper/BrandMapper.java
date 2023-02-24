package com.example.service.mapper;

import com.example.domain.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 张丽璇
 * @date 2023/2/16
 */
public interface BrandMapper {
    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();
    /**
     * 查看详情：根据id查询
     */
    Brand selectById(int id);

    /**
     * 散装参数：@Param("sql中参数占位符的名称")
     */
    List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);

    /**
     * 对象参数 ：sql中参数占位符的名称和类中属性名一致
     */
    List<Brand> selectByCondition(Brand brand);

    /**
     * 集合参数：主键和sql中参数占位符的名称一致
     */
    List<Brand> selectByCondition(Map map);

    /**
     * 单条件的动态查询
     * @param brand
     * @return
     */
    List<Brand> selectByConditionSingle(Brand brand);

    void add(Brand brand);

    int update(Brand brand);

    void deleteById(int id);

    void deleteByIds(@Param("ids") int[] ids);
}
