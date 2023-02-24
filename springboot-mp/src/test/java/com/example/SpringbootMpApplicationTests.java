package com.example;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.AccountDao;
import com.example.dao.BookDao;
import com.example.domain.Account;
import com.example.domain.Book;
import com.example.domain.query.AccountQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMpApplicationTests {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private AccountDao accountDao;

	@Test
	public void testAdd(){
		Book book = new Book();
		book.setName("mp");
		book.setType("计算机");
		book.setDescription("springboot-mp");
		bookDao.insert(book);
	}

	@Test
	public void testGetAll() {
		//方式一：按条件查询
		// QueryWrapper qw = new QueryWrapper();
		// qw.eq("name", "mp");
		// List<Book> books = bookDao.selectList(qw);
		// System.out.println(books);

		//方式二：使用lambda格式条件查询
		// QueryWrapper<Book> qw = new QueryWrapper<>();
		// qw.lambda().eq(Book::getName, "mp");
		// List<Book> books = bookDao.selectList(qw);
		// System.out.println(books);

		//方式三：简化版的lambda格式条件查询
		// LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
		// lqw.eq(Book::getName, "mp");
		// List<Book> books = bookDao.selectList(lqw);
		// System.out.println(books);

		//模拟页面传递过来的数据
		AccountQuery accountQuery = new AccountQuery();
		// accountQuery.setMoney(100.00);
		accountQuery.setMoney2(1100.00);
		LambdaQueryWrapper<Account> lqw = new LambdaQueryWrapper<>();
		lqw.lt(accountQuery.getMoney2() != null, Account::getMoney,accountQuery.getMoney2());
		lqw.gt(accountQuery.getMoney() != null, Account::getMoney, accountQuery.getMoney());
		List<Account> books = accountDao.selectList(lqw);
		System.out.println(books);
	}

	@Test
	public void testAccountAdd(){
		Account account = new Account().setAccount("王五").setMoney(2000.00);
		accountDao.insert(account);
	}

	@Test
	public void testSelectPage(){
		IPage page = new Page(1,2);
		IPage iPage = bookDao.selectPage(page, null);
		System.out.println("当前页码："+iPage.getCurrent());
		System.out.println("每页条数："+iPage.getSize());
		System.out.println("总页数："+iPage.getPages());
		System.out.println("总条数："+iPage.getTotal());
		System.out.println("当前数据："+iPage.getRecords());
	}

	@Test
	public void testDeleteById(){
		accountDao.deleteById(1L);
	}

	@Test
	public void testUpdateAccount(){
		Account account = accountDao.selectById(2L);
		Account account1 = accountDao.selectById(2L);
		account.setMoney(account.getMoney()+100.00);
		account1.setMoney(account1.getMoney()+200.00);
		accountDao.updateById(account);
		accountDao.updateById(account1);
	}

}
