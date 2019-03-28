package com.atguigu.bookstore.service.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;

public class BookServiceImpl implements BookService{
	private BookDao dao = new BookDaoImpl();
	
	@Override
	public Page<Book> findPageByPrice(String pageNumber, int size, String minPrice, String maxPrice) {
		//1、创建分页对象
		Page<Book> page = new Page<Book>();
		//2、将参数进行类型转换
		int pageNum = 1;
		double minPri = 0;
		double maxPri = Double.MAX_VALUE;
		
		try {
			pageNum = Integer.parseInt(pageNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			minPri = Double.parseDouble(minPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			maxPri = Double.parseDouble(maxPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//3、设置数据给page对象
		page.setSize(size);
		page.setPageNumber(pageNum);
		//4、调用dao继续给page对象设置数据[查询数据库中的数据设置给page]
		return dao.getPageByPrice(page, minPri, maxPri);
	}

	@Override
	public Page<Book> findPage(String pageNumber, int size) {
		//1、创建page对象保存分页相关的数据
		Page<Book> page = new Page<Book>();
		//2、将参数设置给page对象   
		//可能会有数字转换异常
		int number = 1;//如果页码转换出现错误，默认显示第一页数据
		try {
			number = Integer.parseInt(pageNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		page.setPageNumber(number);
		page.setSize(size);
		//3、调用dao继续完成page对象属性值的设置  并返回
		return  dao.getPage(page);
	}
	
	@Override
	public boolean editBook(Book book) {
		return dao.updateBookById(book)>0;
	}
	
	@Override
	public Book findBook(String  bookId) {
		return dao.getBookById(bookId);
	}
	
	
	@Override
	public boolean addBook(Book book) {
		return dao.saveBook(book)>0;
	}
	
	@Override
	public List<Book> findAllBooks() {
		//直接通过BookDao即可实现
		return dao.getAllBooks();
	}

	@Override
	public boolean deleteBook(String bookId) {
		int i = dao.deleteBookById(bookId);
		return i>0;
	}




	

	



}
