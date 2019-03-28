package com.atguigu.bookstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HttpFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	@Override
	public void destroy() {
		
	}
	
	//服务器拦截请求时只会调用filter 的生命周期方法doFilter(ServletRequest request, ServletResponse response进行拦截
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//将参数转为Http类型的
		HttpServletRequest   req = (HttpServletRequest) request;
		HttpServletResponse   res = (HttpServletResponse) response;
		doFilter(req, res, chain);
	}

	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException;

}
