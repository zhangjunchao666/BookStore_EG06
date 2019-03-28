package com.atguigu.bookstore.bean;
/**
 * 订单项类 
 * 		对应bs_orderitem表
 * @author Administrator
 *
 */
public class OrderItem {
	/**
	 * 订单项id
	 */
	private Integer id;
	/**
	 * 购买时图书的标题
	 */
	private String title;
	/**
	 * 购买时作者信息
	 */
	private String author;
	/**
	 * 购买时图书封面地址
	 */
	private String imgPath;
	/**
	 * 购买时购物项图书单价
	 */
	private double price;
	/**
	 * 购买时购物项数量
	 */
	private int count;
	/**
	 * 购买时单品总金额
	 */
	private double amount;
	/**
	 * 订单项所属订单的id
	 */
	private String orderId;
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
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public OrderItem(Integer id, String title, String author, String imgPath, int count, double amount,
			String orderId) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.imgPath = imgPath;
		this.count = count;
		this.amount = amount;
		this.orderId = orderId;
	}
	public OrderItem() {
		super();
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", title=" + title + ", author=" + author + ", imgPath=" + imgPath + ", count="
				+ count + ", amount=" + amount + ", orderId=" + orderId + "]";
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
