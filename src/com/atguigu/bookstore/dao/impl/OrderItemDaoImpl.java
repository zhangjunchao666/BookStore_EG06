package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

	@Override
	public int saveOrderItem(OrderItem item) {
		String sql = "INSERT INTO bs_orderitem(title , author , img_path , price , amount , count , order_id)"
				+ " VALUES(? ,? ,? ,? ,? ,? ,?)";
		return update(sql, item.getTitle() , item.getAuthor() , item.getImgPath() , item.getPrice() , item.getAmount() ,
				item.getCount() , item.getOrderId());
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(String orderId) {
		String sql = "SELECT id , title , author , img_path imgPath, price , amount , count , order_id orderId "
				+ " FROM bs_orderitem WHERE order_id = ?";
		return getBeanList(OrderItem.class, sql, orderId);
	}

}
