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

public class JDBCUtils2 {
	/**
	 * 初始化数据库连接池，一个项目中使用一个
	 */
	private static DataSource source; 
	
	static {
		//加载连接参数
		InputStream is = JDBCUtils2.class.getClassLoader().getResourceAsStream("jdbc.properties");
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

	
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	public static Connection getConn() {
		Connection conn = local.get();
		if(conn==null) {
			//第一次使用连接
			try {
				conn = source.getConnection();
				//将线程对象和conn绑定
				local.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	
	//释放线程对象绑定的数据库连接
	public static void closeConn() {
		Connection connection = local.get();
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//移除绑定的数据库连接对象
		local.remove();
		
	}
	
	
	
}
