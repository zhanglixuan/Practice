package com.example.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author 张丽璇
 * @date 2023/2/21
 */
public interface AccountService {
	@Transactional
	void transfer(Integer inCount,Integer outCount, Double money);
}
