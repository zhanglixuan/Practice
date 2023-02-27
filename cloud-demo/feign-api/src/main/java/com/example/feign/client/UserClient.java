package com.example.feign.client;

import com.example.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 张丽璇
 * @date 2023/2/27
 * 创建一个user客户端，做接口声明
 * 封装的是对userservice服务相关的所有的远程调用
 */
@FeignClient("userservice")
public interface UserClient {
	@GetMapping("/users/{id}")
	User queryById(@PathVariable("id") Long id);
}
