package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Order;

/**
 * 约束对bs_order表的操作
 * @author Administrator
 *
 */
public interface OrderDao {
	/**
	 * 保存订单到数据库的方法
	 * @param order
	 * @return
	 */
	int saveOrder(Order order);
	/**
	 * 用户根据自己id查询订单集合的方法
	 * @param userId
	 * @return
	 */
	List<Order> getOrdersByUserId(int userId);
	/**
	 * 管理员查询所有订单的方法
	 * @return
	 */
	List<Order> getAllOrders();
	/**
	 * 管理员或用户修改订单状态的方法
	 * @param orderId
	 * @param state
	 * @return
	 */
	int updateStateByOrderId(String orderId , int state);
	
	
}









