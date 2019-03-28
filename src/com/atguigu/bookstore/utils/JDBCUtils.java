package com.atguigu.bookstore.utils;
/**
 * 管理数据库连接的工具类
 * @author Administrator
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils {
	/**
	 * 初始化数据库连接池，一个项目中使用一个
	 */
	private static DataSource source; 
	
	static {
		//加载连接参数
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		//创建Properties对象 加载is
		Properties info = new Properties();
		try {
			info.load(is);
			//初始化数据库连接池
			source = DruidDataSourceFactory.createDataSource(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//给线程对象绑定连接的map
	private static Map<Thread, Connection> map = new ConcurrentHashMap<Thread, Connection>();
	/**
	 * 获取连接的方法
	 */
	public static Connection getConn() {
		//此方法为每个线程对象分配并管理连接
		//获取当前线程对象对应的数据库连接
		Connection conn = map.get(Thread.currentThread());
		if(conn==null) {
			//线程第一次获取连接  新创建并和线程对象绑定
			try {
				conn = source.getConnection();
				map.put(Thread.currentThread(), conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return conn;
	}
	
	/**
	 * 关闭连接的方法
	 */
	public static void closeConn(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//释放线程对象绑定的数据库连接
	public static void closeConn() {
		Connection conn = map.get(Thread.currentThread());
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		map.remove(Thread.currentThread());
	}
	
	
	
}
