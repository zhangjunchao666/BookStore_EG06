package com.atguigu.bookstore.bean;

import java.io.Serializable;

public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 图书id
	 */
	private Integer id;
	/**
	 * 图书标题
	 */
	private String title;
	/**
	 * 图书作者
	 */
	private String author;
	/**
	 * 图书销量
	 */
	private int sales;
	/**
	 * 图书库存
	 */
	private int stock;
	/**
	 * 图书单价
	 */
	
	private double price;
	/**
	 * 图书封面地址
	 */
	private String imgPath;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Book(Integer id, String title, String author, int sales, int stock, double price, String imgPath) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.sales = sales;
		this.stock = stock;
		this.price = price;
		this.imgPath = imgPath;
	}
	public Book() {
		super();
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", sales=" + sales + ", stock=" + stock
				+ ", price=" + price + ", imgPath=" + imgPath + "]";
	}
	
	
	
}
