package com.atguigu.bookstore.service;

import com.atguigu.bookstore.bean.User;

/**
 * 用户相关业务的约束
 * @author Administrator
 *
 */
public interface UserService {
	/**
	 * 登录的业务方法
	 * @param user
	 * @return
	 */
	User login(User user);
	/**
	 * 注册的业务方法
	 * @param user
	 * @return
	 */
	boolean regist(User user);
	
	/**
	 * 异步校验用户名
	 */
	int  checkUsername(String username);
}
