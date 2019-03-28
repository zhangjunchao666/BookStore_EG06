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
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;

/**
 * 检查访问OrderClientServlet的请求，是否已登录
 */
public class LoginFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
   		User user = (User) session.getAttribute("user");
		if(user==null) {
   			String errorMsg = "订单操作必须登录！！------";
			request.setAttribute("errorMsg", errorMsg);
			//登录失败    转发到login.html页面让用户继续登录
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
   		}else {
   			//已登录放行给目标资源处理
   			chain.doFilter(request, response);
   		}
	}

   
}
