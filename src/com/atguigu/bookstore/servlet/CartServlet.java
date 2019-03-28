package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.google.gson.Gson;

/**
 * 处理用户购物车相关的请求
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private BookService service = new BookServiceImpl();
    
    /**
     * 处理修改指定购物项数量的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String bookId = request.getParameter("bookId");
    	String count = request.getParameter("count");
    	
    	HttpSession session = request.getSession();
    	Cart cart = (Cart) session.getAttribute("cart");
    	cart.updateCountByBookId(bookId, count);
    	//跳转回之前的页面
    	response.sendRedirect(request.getHeader("referer"));
    	
    }
    
    
    /**
     * 处理清空 购物车请求
     */
    protected void clearCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	Cart cart = (Cart) session.getAttribute("cart");
    	cart.clearCart();
    	//跳转回之前的页面
    	response.sendRedirect(request.getHeader("referer"));
	}
    /**
	 * 删除指定购物项
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
    protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String bookId = request.getParameter("bookId");
    	//获取session中的购物车对象
    	HttpSession session = request.getSession();
    	Cart cart = (Cart) session.getAttribute("cart");
    	cart.deleteCartItemByBookId(bookId);
    	//跳转回之前的页面
    	response.sendRedirect(request.getHeader("referer"));
    }
    /**
     * 处理添加图书到购物车的请求   
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断浏览器是否已经有对应的购物车对象了
		//浏览器的购物车对象只能使用一个，一个浏览器只对应一个Session，将购物车对象存到session中
		//1、直接从Session中获取购物车对象
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		//2、判断cart是否为null
		if(cart==null) {
			//浏览器第一次使用购物车，新创建购物车对象分配给浏览器
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		//3、使用购物车对象的方法将要添加的图书存到map中
		//获取要添加图书的id
		String bookId = request.getParameter("bookId");
		//bookService 根据bookId查询对应的图书对象
		Book book = service.findBook(bookId);
		//为了跳转之后的页面能够显示最新添加的图书标题 可以将图书title存到session中共享
		session.setAttribute("title", book.getTitle());
		cart.addBook2Cart(book);
		
		// 获取到书的名字
		String title = book.getTitle();
		// 获取到购物车中的商品个数
		Integer  totalCount = cart.getTotalCount();
		
		Map<String,Object> map = new  HashMap<>();
		map.put("title", title);
		map.put("totalCount", totalCount);
		
		Gson gson = new Gson();
		
		String jsonStr  = gson.toJson(map);
		
		PrintWriter out = response.getWriter();
		
		out.println(jsonStr);
		
		out.close();
		//4、给用户响应：跳转回之前的页面
		//response.sendRedirect(request.getHeader("referer"));
		
		
	
	}

}
