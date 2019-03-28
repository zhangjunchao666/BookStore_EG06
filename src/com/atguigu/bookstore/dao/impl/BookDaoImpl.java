package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;

public class BookDaoImpl extends BaseDao implements BookDao {


	@Override
	public Page<Book> getPageByPrice(Page<Book> page, double minPrice, double maxPrice) {
		//1、查询价格区间的图书的总数量并设置给page
		String countSql = "SELECT COUNT(*) FROM bs_book WHERE price BETWEEN ? AND ?";
		int totalCount = getCount(countSql, minPrice , maxPrice);
		page.setTotalCount(totalCount);
		//2、查询价格区间的分页图书集合
		String sql = "SELECT id , title , author , price , sales , stock , img_path imgPath "
				+ "FROM bs_book WHERE price BETWEEN ? AND ? LIMIT ? , ?";
		List<Book> list = getBeanList(Book.class, sql, minPrice , maxPrice , page.getIndex() , page.getSize());
		page.setData(list);
		//3、返回封装之后的page对象
		return page;
	}


	@Override
	public Page<Book> getPage(Page<Book> page) {
		//1、查询图书的记录总条数设置给page对象
		String countSql = "SELECT COUNT(*) FROM bs_book";
		int totalCount = getCount(countSql);
		page.setTotalCount(totalCount);
		//2、查询分页需要的图书集合设置给page对象
		String sql = "SELECT id , title , author , price , sales , stock , img_path imgPath "
				+ "FROM bs_book LIMIT ? , ?";
		//查询分页数据时  需要使用index=(pageNumber-1)*size    (10-1)*4   (9-1)*4
		List<Book> data = getBeanList(Book.class, sql, page.getIndex() , page.getSize());
		page.setData(data);
		//3、返回封装完成的page对象
		return page;
	}
	
	@Override
	public int saveBook(Book book) {
		String sql = "INSERT INTO bs_book(title , author , price , sales , stock , img_path) VALUES(?"
				+ " , ? ,? , ? ,? ,? )";
		return update(sql, book.getTitle() , book.getAuthor() , book.getPrice() ,
				book.getSales() , book.getStock() , book.getImgPath());
	}

	@Override
	public int deleteBookById(String bookId) {
		String sql = "DELETE FROM bs_book WHERE id = ? ";
		return update(sql, bookId);
	}

	@Override
	public int updateBookById(Book book) {
		String sql = "UPDATE bs_book SET title =?  , author=? , price=? , sales=? , stock=? , img_path=? "
				+ "WHERE id = ? ";
		return update(sql, book.getTitle() , book.getAuthor() , book.getPrice() ,
				book.getSales() , book.getStock() , book.getImgPath(),book.getId());
	}

	@Override
	public Book getBookById(String bookId) {
		/**
		 * 所有的查询 封装对象时 需要考虑 查询的列名和javabean的属性名是否一致
		 * 
		 * BaseDao的getBean方法查询数据时 会将查询到的数据封装位对象
		 * 
		 * BeanHandler会将查询到的数据封装位Book对象
		 * 	- 根据查询到的数据的列名去匹配Book类的setXxx方法
		 * 		setImgPath 和 数据库查询的列名匹配失败 img_path	 	
		 */
		String sql = "SELECT id , title , author , price , sales , stock , img_path imgPath"
				+ " FROM bs_book WHERE id = ?";
		return getBean(Book.class, sql, bookId);
	}

	@Override
	public List<Book> getAllBooks() {
		String sql = "SELECT id , title , author , price , sales , stock , img_path imgPath"
				+ " FROM bs_book";
		return getBeanList(Book.class, sql);
	}


}
