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

/**
 */
public class EncodingFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//post请求乱码
		request.setCharacterEncoding("UTF-8");
		//响应乱码
		response.setContentType("text/html;charset=UTF-8");
		
		
		chain.doFilter(request, response);
	}

    

}
