package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车类
 * 	一个用户对应一个购物车[只要将购物车对象共享到session中]
 * 	一个购物车中可以有多个购物项
 *
 */
public class Cart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//键：购物项图书的id  
	/**
	 * 存储购物项的map集合
	 */
	private Map<String , CartItem> map = new LinkedHashMap<String , CartItem>();
	/**
	 * 总数量：
	 * 购物项集合遍历累加得到
	 */
	private int totalCount;
	/**
	 * 总金额：
	 * 	遍历购物项集合累加得到
	 */
	private double totalAmount;
	public Map<String, CartItem> getMap() {
		return map;
	}
	/*public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}*/
	//遍历累加购物项数量
	public int getTotalCount() {
		//重置总数量
		totalCount = 0;
		for(CartItem item:getCartItemList()) {
			totalCount+=item.getCount();
		}
		
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	//遍历累加总数量
	public double getTotalAmount() {
		//先将要计算的 double类型数据转为BigDecimal对象
		totalAmount = 0;
		BigDecimal bd1 = new BigDecimal(totalAmount+"");
		for(CartItem item:getCartItemList()) {
			//totalAmount+=item.getAmount();
			BigDecimal bd2 = new BigDecimal(item.getAmount()+"");
			bd1 = bd1.add(bd2);
			
		}
		//bd1代表累加之后的结果
		totalAmount = bd1.doubleValue();
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Cart(int totalCount, double totalAmount) {
		super();
		this.totalCount = totalCount;
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "Cart [map=" + map + ", totalCount=" + totalCount + ", totalAmount=" + totalAmount + "]";
	}
	public Cart() {
		super();
	}
	//购物车的操作 就是操作map集合的数据，所以相关的业务方法直接写在购物车中
	//1、将购物项集合转为List的方法
	public List<CartItem> getCartItemList(){
		Collection<CartItem> values = map.values();
		return new ArrayList<>(values);
	}
	//2、添加图书到购物车的方法
	public void addBook2Cart(Book book) {
		//判断图书在map中是否已存在对应的购物项
		CartItem cartItem = map.get(book.getId()+"");
		if(cartItem==null) {
			//图书第一次添加到购物车中
			//将图书转为购物项存到map中
			cartItem = new CartItem(book, 1);//第一次添加默认数量为1
			map.put(book.getId()+"", cartItem);
		}else {
			//购物项已存在，在原有数量上+1
			cartItem.setCount(cartItem.getCount()+1);
		}
	}
	//3、清空购物车[本质就是清空map集合]
	public void clearCart() {
		map.clear();
	}
	//4、删除指定图书id对应的购物项
	public void deleteCartItemByBookId(String bookId) {
		map.remove(bookId);
	}
	//5、更新指定图书id对应的购物项的数量
	public void updateCountByBookId(String bookId , String count) {
		//获取要修改的购物项
		CartItem cartItem = map.get(bookId);
		
		int parseCount = cartItem.getCount();//默认数量为修改之前的正确数量
		try {
			parseCount = Integer.parseInt(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(parseCount==0) {
			map.remove(bookId);
		}else {
			if(cartItem!=null) {
				cartItem.setCount(parseCount);
			}
		}
	}
	
	
	
	
	
	
}
