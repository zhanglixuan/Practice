package com.example.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author 张丽璇
 * @date 2023/2/21
 */
@Repository
public interface AccountDao {
	@Update("UPDATE account SET money=money+#{money} WHERE id=#{inId}")
	Integer add(@Param("inId") Integer inId, @Param("money") Double money);
	@Update("UPDATE account SET money=money-#{money} WHERE id=#{outId}")
	Integer sub(@Param("outId") Integer outId, @Param("money") Double money);
}
