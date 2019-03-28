package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;

public class UserDaoTest {
	private UserDao dao = new UserDaoImpl();
	@Test
	public void testSave() {
		//模拟注册操作
		User user = new User(null, "admin", "123456", "admin@126.com");
		//调用UserDao的saveUser方法将数据保存到数据库中
		int i = dao.saveUser(user);
		System.out.println(i);
	}
	@Test
	public void testSelect() {
		//模拟登录操作
		User user = new User(null, "admin", "1234562", null);
		User u = dao.getUserByUsernameAndPassword(user);
		System.out.println(u);
	}

}
