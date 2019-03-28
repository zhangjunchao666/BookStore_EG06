package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao dao = new UserDaoImpl();
	@Override
	public User login(User user) {
		return dao.getUserByUsernameAndPassword(user);
	}

	@Override
	public boolean regist(User user) {
		return dao.saveUser(user)>0;
	}
	
	@Override
	public int checkUsername(String username) {
		return  dao.getUserByUsername(username)==null ? 1 : 0 ; 
				
	}

}
