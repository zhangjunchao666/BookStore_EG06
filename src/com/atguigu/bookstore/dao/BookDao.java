package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

/**
 * 对book表操作的约束
 *
 */
public interface BookDao {
	/**
	 * 按价格区间查询分页数据的方法
	 * @param page
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	Page<Book> getPageByPrice(Page<Book> page , double minPrice , double maxPrice);
	/**
	 * 查询数据库图书 分页数据的方法
	 * @param page
	 * @return
	 */
	Page<Book> getPage(Page<Book> page);
	
	/**
	 * 保存图书到数据库
	 * @param book
	 * @return
	 */
	int saveBook(Book book);
	/**
	 * 根据id删除指定图书
	 * @param bookId
	 * @return
	 */
	int deleteBookById(String bookId);
	/**
	 * 更新执行id的图书
	 * @param book    用户修改过的图书 数据封装而成的对象[无论有没有修改，所有要修改图书的属性值都会上传提交给服务器]
	 * @return
	 */
	int updateBookById(Book book);
	/**
	 * 查询指定id对应的图书
	 * @param bookId
	 * @return
	 */
	Book getBookById(String bookId);
	/**
	 * 查询所有图书的方法
	 * @return
	 */
	List<Book> getAllBooks();
}
