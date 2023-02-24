package com.example.service.impl;

import com.example.dao.AccountDao;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张丽璇
 * @date 2023/2/21
 */
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDao accountDao;

	public void transfer(Integer inId, Integer outId, Double money) {
		accountDao.add(inId,money);
		int i = 1/0;
		accountDao.sub(outId, money);
	}
}
