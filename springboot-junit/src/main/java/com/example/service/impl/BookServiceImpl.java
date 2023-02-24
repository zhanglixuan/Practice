package com.example.service.impl;

import com.example.service.BookService;
import org.springframework.stereotype.Service;

/**
 * @author 张丽璇
 * @date 2023/2/24
 */
@Service
public class BookServiceImpl implements BookService {
	@Override
	public void save() {
		System.out.println("book save running...");
	}
}
