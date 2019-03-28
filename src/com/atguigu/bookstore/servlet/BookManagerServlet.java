package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * 处理管理员图书的请求
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private BookService service = new BookServiceImpl();   
    /**
     * 处理查询分页请求数据的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void findPage(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//1、获取请求参数  页码
    	String pageNumber = request.getParameter("pageNumber");
    	//定义每页显示的记录条数
    	int size = 4;
    	//2、调用dao处理查询分页数据的业务
    	Page<Book> page = service.findPage(pageNumber, size);
    	//将拼接的路径绑定给page对象
    	page.setPath(WebUtils.getPath(request));
    	//以后再进行分页时  需要给page对象设置7个属性值
    	//3、将page对象共享到request域中
    	request.setAttribute("page", page);
    	//4、转发到book_manager.jsp页面显示分页数据
    	//findAllBooks方法已经作废，不再使用 ，   book_manager.jsp页面修改为获取域中的page对象进行显示
    	request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    	
    }

    /**
     * 修改图书的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void editBook(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//获取请求参数
    	Book book = WebUtils.param2Bean(new Book(), request);
    	System.out.println(book);
    	boolean b = service.editBook(book);
    	//跳转到显示所有图书的页面
    	//response.sendRedirect(request.getContextPath()+"/BookManagerServlet?type=findPage&pageNumber=1");
    	//从请求参数中 获取修改之前的页面地址
    	String ref = request.getParameter("ref");
    	//重定向回修改之前的页面
    	response.sendRedirect(ref);
    }
    
    //处理查询指定图书的请求
    protected void findBook(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//获取上一个页面地址[修改之前的页面地址]
    	String referer = request.getHeader("referer");
    	//共享到request域中交给book_edit.jsp页面
    	request.setAttribute("ref", referer);
    	//1、获取要修改图书的id
    	String bookId = request.getParameter("bookId");
    	System.out.println(bookId);
    	//2、调用service处理业务
    	Book book = service.findBook(bookId);
    	//3、设置到request域中共享
    	request.setAttribute("book", book);
    	//4、转发到book_edit.jsp修改图书的页面 显示要修改的图书信息
    	request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    	
    }
    /**
     * 处理添加图书请求的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addBook(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//1、获取要添加的图书数据
    	//获取的字段过多，   提交的参数name属性值和 javabean的属性名一样
    	//BeanHandler会根据查询到数据的字段名匹配javabean的setXXX方法，将属性值设置给对应的属性
    	//BeanUtils： 可以将Map中的参数设置给javabean的实例，  根据map的key去匹配javabean的属性名 获取对应的set方法将key对应的值设置给对象的属性
    	//String title = request.getParameter("title");
    	Book book = WebUtils.param2Bean(new Book(), request);
    	//2、调用service处理添加图书的业务
    	boolean b = service.addBook(book);
    	
    	//3、给用户响应   [让浏览器跳转到 显示所有图书的页面]
    	response.sendRedirect(request.getContextPath()+"/BookManagerServlet?type=findPage&pageNumber=1");
    	
    }
    
    
    /**
    * 处理管理员查询所有图书的请求
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException
    */
	/*protected void findAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数  不用
		//2、调用service处理业务
		List<Book> list = service.findAllBooks();
		//将查询到的list集合共享到request域中
		request.setAttribute("list", list);
		//3、转发到/pages/manager/book_manager.jsp给管理员响应图书显示的页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
		
	}*/
	protected void deleteBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		//调用业务方法处理删除的业务
		boolean b = service.deleteBook(bookId);
		//跳转后删除之前的页面
		//获取referer：删除之前的页面地址
		String referer = request.getHeader("referer");
		//必须使用重定向
		response.sendRedirect(referer);
	}
	
}
