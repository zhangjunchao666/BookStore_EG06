package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

/**
 * 处理用户相关的请求
 * 
 * 步骤：
			1、创建UserServlet
			2、在UserServelt提供login和regist方法[直接从LoginServlet和RegistServlet中拷贝，修改方法名]
			3、在UserServlet的doGet方法中通过反射 根据type参数值调用对应的方法
			4、login.jsp和regist.jsp页面中的action地址 修改为UserServlet，并提供一个隐藏表单项携带type参数
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	//处理登录请求的方法
	private UserService service = new UserServiceImpl();

	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//销毁内存中的session对象
		HttpSession session = request.getSession();
		session.invalidate();
		//跳转到首页
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取登录的参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User login = new User(null, username, password, null);
		//2、[调用UserDao查询处理登录请求]     调用UserService处理业务逻辑   
		User user = service.login(login);
		//User user = dao.getUserByUsernameAndPassword(login);//返回的是在数据库中查询到的用户对象
		//3、根据处理结果给用户响应
		if(user==null) {
			//登录失败 可以在reuqest域中设置失败消息
			String errorMsg = "账号或密码错误！！";
			request.setAttribute("errorMsg", errorMsg);
			//登录失败    转发到login.html页面让用户继续登录
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
			//将user共享到session中，保持登录状态
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			//登录成功  重定向到成功页面
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}
	}
	//处理注册请求的方法
	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//①获取用户页面中提交的验证码参数
		String code = request.getParameter("code");
		//②获取服务器存在session中的验证码字符串[注册表单中的img标签去获取验证码图片时访问KaptchaServlet时servlet存储的，key默认是]
		HttpSession session = request.getSession();
		String serverCode = (String)session.getAttribute("KAPTCHA_SESSION_KEY");
		//④移除服务器session中保存的已使用过的验证码字符串
		session.removeAttribute("KAPTCHA_SESSION_KEY");
		//③比较验证码是否正确
		if(code!=null && code.equals(serverCode)) {
			//验证码正确并且不是重复提交，处理注册请求
			//1、获取regist.html页面提交的请求参数
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			//将参数封装到user对象中
			User user = new User(null, username, password, email);
			//2、[调用DAO将数据保存到数据库中] 调用UserService处理注册业务
			boolean b = service.regist(user);
			//int i = dao.saveUser(user);
			//3、根据处理结果给用户响应
			if(b) {
				//注册成功  重定向到regist_success.html页面
				//重定向绝对路径交给浏览器解析：基准地址 到服务器  http://localhost:8080
				response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
			}else {
				//注册失败 设置错误消息 交给注册页面显示
				String errorMsg = "用户名已存在！！！";
				request.setAttribute("errorMsg", errorMsg);
				//注册失败  转发发 regist.html页面让用户继续注册
				//转发绝对路径由服务器解析：基准地址到项目    http://localhost:8080/项目名
				//转发会造成转发后的资源相对路径失效
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			}
		}else {
			//重复提交或验证码错误，转发到注册页面让用户继续注册，并给出错误提示
			//注册失败 设置错误消息 交给注册页面显示
			String errorMsg = "验证码错误！！！";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}
	/**
	 *  注册操作异步校验用户名
	 */
	protected void checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取到用户名
		String  username = request.getParameter("username");
		
		int result = service.checkUsername(username);
		
		System.out.println("result :  " + result );
		
		PrintWriter out = response.getWriter();	
		
		out.println(result);
		
		out.close();
	}
	
}
