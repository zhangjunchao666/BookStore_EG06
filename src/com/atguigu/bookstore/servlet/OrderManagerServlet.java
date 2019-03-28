package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * 处理管理员订单相关的请求方法
 */
public class OrderManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
     private OrderService service = new OrderServiceImpl(); 
     
    /**
     * 处理管理员发货的请求方法 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void sendGoods(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String orderId = request.getParameter("orderId");
    	int state = 1;//管理员发货就是讲订单的状态值改为1
    	boolean b = service.sendGoods(orderId, state);
    	//跳转回之前的页面
    	response.sendRedirect(request.getHeader("referer"));
    }
     
    /**
     * 处理查询所有订单的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	protected void findAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> list = service.findAllOrders();
		// 在/pages/manager/order_manager.jsp中显示订单集合
		request.setAttribute("list", list);
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
	}

}
