package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.bean.User;

/**
 * 约束对bs_user表的操作
 *
 */
public interface UserDao {
		
	/**
	 * 登录方法
	 * @param user
	 * @return
	 */
	User getUserByUsernameAndPassword(User user);
	/**
	 * 注册方法
	 * @param user
	 * @return
	 */
	int saveUser(User user);
	
	/**
	 * 注册  异步校验用户名
	 */
	User getUserByUsername(String username );

}
