package com.atguigu.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.utils.JDBCUtils;

/**
 * 统一处理事务的filter
 */
public class TransactionFilter extends HttpFilter {

   
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//1、给当前线程对象绑定连接
		Connection conn = JDBCUtils.getConn();
		try {
			//2、开启事务
			conn.setAutoCommit(false);
			//3、执行后续的代码  多个sql操作
			chain.doFilter(request, response);
			//4、正常执行没有异常，提交
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//5、有异常，回滚
			try {
				conn.rollback();
				//给用户跳转到错误页面并提示
				response.sendRedirect(request.getContextPath()+"/pages/error/error.jsp");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			JDBCUtils.closeConn();
		}
	}

}
