package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;

public class BookDaoTest {
	private BookDao dao = new BookDaoImpl();
	@Test
	public void testSave() {
		Book book1 = new Book(1, "java从入门到转行", "刘优", 0,1000 , 10.0	, "/static/img/default.jpg");
		Book book2 = new Book(2, "HTML5从入门到转行", "婷姐", 100,1000 , 0.1	, "/static/img/default.jpg");
		Book book3 = new Book(3, "Android从入门到转行", "阿福", 0,1000 , 10.0	, "/static/img/default.jpg");
		Book book4 = new Book(4, "Python从入门到转行", "苏欣", 0,1000 , 0.1	, "/static/img/default.jpg");
		int i = dao.saveBook(book1);
		System.out.println(i);
		dao.saveBook(book2);
		dao.saveBook(book3);
		dao.saveBook(book4);
	}
	@Test
	public void testSelect() {
		//用户提交要查询的图书id
		String id = "3";
		Book book = dao.getBookById(id);
		System.out.println(book);
		
	}
	@Test
	public void testSelectAll() {
		List<Book> list = dao.getAllBooks();
		System.out.println(list);
	}
	@Test
	public void testUpdate() {
		//数据库中已存在的图书
		String id = "3";
		Book book = dao.getBookById(id);
		
		//修改图书
		book.setAuthor("褚澳");
		book.setPrice(0.2);
		System.out.println(book);
		int i = dao.updateBookById(book);
		System.out.println(i);
	}
	@Test
	public void testDelete() {
		String id = "1";
		int i = dao.deleteBookById(id);
		System.out.println(i);
		
	}

}
