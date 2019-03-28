package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.atguigu.bookstore.utils.JDBCUtils;

public class JDBCUtilsTest {

	@Test
	public void test() {
		//获取连接
		Connection conn = JDBCUtils.getConn();
		//使用连接
		System.out.println(conn);
		//关闭连接  [将连接还给连接池]
		JDBCUtils.closeConn(conn);
	}

}
