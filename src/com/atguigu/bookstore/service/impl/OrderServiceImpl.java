package com.atguigu.bookstore.service.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	@Override
	public String createOrder(Cart cart, User user) {
		//1、将购物车转为订单对象  并保存到数据库
		//创建订单id：   时间戳+""+userid
		String id = System.currentTimeMillis()+""+user.getId();
		//订单创建的时间：当前
		Date orderTime = new Date();
		//订单的状态： 默认未发货 0
		int state = 0;
		Order order = new Order(id, orderTime, state, cart.getTotalCount(), cart.getTotalAmount(), user.getId());
		orderDao.saveOrder(order);
		//2、将购物项集合转为订单项并保存到数据库中
		List<CartItem> cartItemList = cart.getCartItemList();
		for (CartItem cartItem : cartItemList) {
			Book book = cartItem.getBook();
			//每个购物项对应一个订单项
			OrderItem orderItem = new OrderItem(null, book.getTitle(), book.getAuthor(), book.getImgPath(), cartItem.getCount(), cartItem.getAmount(), order.getId());
			orderItemDao.saveOrderItem(orderItem);
			//3、修改订单项对应的图书的销量库存
			book.setSales(book.getSales()+cartItem.getCount());//之前的销量+cartItem.getCount() 
			book.setStock(book.getStock()-cartItem.getCount());
			bookDao.updateBookById(book);
		}
		//4、清空购物车
		cart.clearCart();
		//5、返回订单id
		return id;
	}
	@Override
	public List<Order> findOrder(int userId) {
		return orderDao.getOrdersByUserId(userId);
	}
	@Override
	public boolean takeGoods(String orderId, int state) {
		return orderDao.updateStateByOrderId(orderId, state)>0;
	}
	@Override
	public List<Order> findAllOrders() {
		return orderDao.getAllOrders();
	}
	@Override
	public boolean sendGoods(String orderId, int state) {
		return orderDao.updateStateByOrderId(orderId, state)>0;
	}

}
