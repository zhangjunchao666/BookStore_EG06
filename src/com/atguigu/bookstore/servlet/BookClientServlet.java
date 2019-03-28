package com.atguigu.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * 处理用户 图书相关的请求
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookService service = new BookServiceImpl();
	/**
	 * 处理用户按价格区间查询分页数据的请求
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findPageByPrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		String minPrice = request.getParameter("minPrice");
		String maxPrice = request.getParameter("maxPrice");
		int size = 4;
		//调用service处理业务
		Page<Book> page = service.findPageByPrice(pageNumber, size, minPrice, maxPrice);
		//给page绑定path路径
		page.setPath(WebUtils.getPath(request));
		//将page共享到request域中
		request.setAttribute("page", page);
		//转发到list.jsp页面显示分页数据
		request.getRequestDispatcher("/pages/list/list.jsp").forward(request, response);
	}
	
	/**
     * 处理用户查询分页数据的请求   
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	protected void findPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		int size = 4;
		Page<Book> page = service.findPage(pageNumber, size);
		//一定要给page对象绑定path，否则分页导航栏不能使用
		page.setPath(WebUtils.getPath(request));
		
		//将page对象设置到request域中
		request.setAttribute("page", page);
		//转发到index页面给用户显示分页数据
		request.getRequestDispatcher("/pages/list/list.jsp").forward(request, response);
	
	}

}
