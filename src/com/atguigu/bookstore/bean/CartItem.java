package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物项
 * 	一个购物车中可以有多个购物项
 * 	一个购物项对应一本图书信息
 *
 */
public class CartItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 购物项对应图书的信息
	 */
	private Book book;
	/**
	 * 单项的总数量
	 */
	private int count;
	/**
	 * 单品总金额：计算得到
	 */
	private double amount;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		BigDecimal bd1 = new BigDecimal(getBook().getPrice()+"");
		BigDecimal bd2 = new BigDecimal(getCount()+"");
		
		
		amount = bd1.multiply(bd2).doubleValue();
		return amount;
	}
	/*public void setAmount(double amount) {
		this.amount = amount;
	}*/
	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + ", amount=" + amount + "]";
	}
	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}
	public CartItem() {
		super();
	}
	
}
