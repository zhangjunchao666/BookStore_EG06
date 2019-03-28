package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

/**
 * 约束图书操作的相关业务
 *
 */
public interface BookService {
	/**
	 * 查询价格区间分页数据的业务方法
	 * @param pageNumber
	 * @param size
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	Page<Book> findPageByPrice(String pageNumber , int size ,String minPrice , String maxPrice);
	/**
	 * 查询分页数据的业务方法
	 * @param pageNumber
	 * @param size
	 * @return
	 */
	Page<Book> findPage(String pageNumber , int size);
	
	/**
	 * 修改图书的业务方法
	 * @param book
	 * @return
	 */
	boolean editBook(Book book);
	/**
	 * 查询指定图书的业务方法
	 * @return
	 */
	Book findBook(String  bookId);
	/**
	 * 管理员添加图书的业务方法
	 * @param book
	 * @return
	 */
	boolean addBook(Book book);
	
	
	/**
	 * 管理员查询所有图书集合的方法
	 * @return
	 */
	List<Book> findAllBooks();
	/**
	 * 管理员删除图书的业务方法
	 * @param bookId
	 * @return
	 */
	boolean deleteBook(String bookId);
}	
