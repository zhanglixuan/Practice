package com.example.controller;

import com.example.service.BookService;
import com.example.domain.Book;
import com.example.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张丽璇
 * @date 2023/2/22
 */
@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;

	@PostMapping
	public Result save(@RequestBody Book book) {
		boolean data = bookService.save(book);
		return new Result(data?Code.AAD_OK:Code.AAD_ERR,data);
	}

	@PutMapping
	public Result update(@RequestBody Book book) {
		boolean data = bookService.update(book);
		return new Result(data?Code.UPDATE_OK:Code.UPDATE_ERR,data);
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Integer id) {
		boolean data = bookService.delete(id);
		return new Result(data?Code.DELETE_OK:Code.DELETE_ERR,data);
	}

	@GetMapping
	public Result getAll() {
		List<Book> all = bookService.getAll();
		Integer code = all != null ? Code.GET_OK : Code.GET_ERR;
		String msg = all !=null?"":"查询失败，请重新查询";
		return new Result(code, all, msg);
	}

	@GetMapping("/{id}")
	public Result getById(@PathVariable Integer id) {
		//最土的办法：可以利用try...catch进行包装，转换成自定义异常
		//模拟异常
		if(id < 0){
			throw new BusinessException(Code.BUSINESS_ERR,"请勿进行非法操作！");
		}
		int i = id / 0;
		Book data = bookService.getById(id);
		Integer code = data != null ? Code.GET_OK : Code.GET_ERR;
		String msg = data !=null ? "" : "查询失败，请重新查询";
		return new Result(code, data, msg);
	}
}
