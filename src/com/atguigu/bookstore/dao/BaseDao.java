package com.atguigu.bookstore.dao;
/**
 * 操作数据库的基类，所有的数据库操作都是调用此类中的方法实现的
 *
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.utils.JDBCUtils;

public class BaseDao {
	//创建数据库操作工具类对象
	private QueryRunner runner = new QueryRunner();
	/**
	 * 通用的增删改
	 * @return
	 */
	public int update(String sql , Object...params) {
		Connection conn = JDBCUtils.getConn();
		int i = 0;
		try {
			i = runner.update(conn, sql, params);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		//如果i>0代表增删改成功
		return i;
	}
	
	/**
	 * 通用的查询单条记录封装位一个java对象的方法
	 */
	public  <T>T  getBean(Class<T> type,String sql , Object...params){
		Connection conn = JDBCUtils.getConn();
		/**
		 * ResultSetHandler：
		 * 	需要传入数据封装的类名
		 * 		可以将查询到的数据通过反射设置给一个对象
		 * 		- 根据查询到的数据的列名去java类中匹配对应的setXXX方法，将值设置给对象
		 * 				如果查询到的列名和java类的属性名(setXXX)不一样，值会设置失败
		 * 	BeanHandler:  将一条记录封装位一个对象
		 * 	BeanListHandler  将多条记录封装位对象的集合
		 * 	ScalarHandler   获取第一行指定列的数据
		 */
		T t = null;
		try {
			t = runner.query(conn, sql, new BeanHandler<>(type), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		//返回null代表查询失败
		return t;
	}
	
	/**
	 * 查询多条记录封装位对象集合的方法
	 */
	public <T>  List<T>  getBeanList(Class<T> type,String sql , Object...params){
		Connection conn = JDBCUtils.getConn();
		List<T> list = null;
		try {
			list = runner.query(conn, sql, new BeanListHandler<>(type), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return list;
	}
	/**
	 * 查询  表记录条数的方法
	 */
	public int getCount(String sql , Object...params) {
		Connection conn = JDBCUtils.getConn();
		long count = 0;
		try {
			//查询数量时，queryrunner默认将数量当做long类型处理，封装成Long的对象，然后再提升为Object类型返回
			count = (long)runner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return (int) count;
	}
	
	
	
	
	
	
}
