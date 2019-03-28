package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;

/**
 * 处理订单相关的业务
 * @author Administrator
 *
 */
public interface OrderService {
	/**
	 * 管理员查询所有订单的业务方法
	 * @return
	 */
	List<Order> findAllOrders();
	
	/**
	 * 收货的业务方法
	 */
	boolean takeGoods(String orderId , int state);
	
	/**
	 * 管理员发货的业务方法
	 */
	boolean sendGoods(String orderId , int state);
	
	/**
	 * 创建订单的业务方法
	 * @param cart
	 * @param user
	 * @return
	 */
	String createOrder(Cart cart  , User user);
	/**
	 * 查询用户订单的业务方法
	 * @param userId
	 * @return
	 */
	List<Order> findOrder(int userId);
}
