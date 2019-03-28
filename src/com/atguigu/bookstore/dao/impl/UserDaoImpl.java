package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.UserDao;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public User getUserByUsernameAndPassword(User user) {
		String sql = "SELECT id , username , password , email FROM bs_user "
				+ " WHERE username=? AND password=?";
		return getBean(User.class, sql, user.getUsername() , user.getPassword());
	}

	@Override
	public int saveUser(User user) {
		try {
			String sql = "INSERT INTO bs_user(username , password , email)  VALUES(? ,? ,?) ";
			return update(sql, user.getUsername() , user.getPassword(),user.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
			return 0 ;
		}
	}
	@Override
	public User getUserByUsername(String username) {
		String sql = "SELECT id , username , password , email FROM bs_user "
				+ " WHERE username=? ";
		
		return getBean(User.class, sql, username);
	}
}
