package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * 处理用户订单相关的请求
 */
public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
   	private OrderService service = new OrderServiceImpl();   
    /**
     * 处理用户收货的请求方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
   	protected void takeGoods(HttpServletRequest request, HttpServletResponse response)
   			throws ServletException, IOException {
   		String orderId = request.getParameter("orderId");
   		//用户只能收货：就是将订单状态改为2
   		int state = 2;
   		boolean flag = service.takeGoods(orderId, state);
   		//跳转回之前的页面
   		response.sendRedirect(request.getHeader("referer"));
   	}
   	
   	/**
   	 * 处理用户查询自己的所有订单的请求
   	 * @param request
   	 * @param response
   	 * @throws ServletException
   	 * @throws IOException
   	 */
   	protected void findOrders(HttpServletRequest request, HttpServletResponse response)
   			throws ServletException, IOException {
   		HttpSession session = request.getSession();
   		User user = (User) session.getAttribute("user");
		List<Order> list = service.findOrder(user.getId());
		//转发到/pages/order/order.jsp 显示订单集合
		request.setAttribute("list", list);
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
   	}
   	
   	/**
    * 处理用户的结账请求
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException
    */
	protected void checkOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//不需要参数：  需要使用的数据都存在session中
		//1、获取session中的cart和user
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");
		//2、判断用户是否登录
		//已登录
		//3、调用service处理业务逻辑
		String orderId = service.createOrder(cart, user);
		//4、将订单id交给/pages/cart/checkout.jsp显示   重定向
		session.setAttribute("orderId", orderId);
		response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
		
	}

}
