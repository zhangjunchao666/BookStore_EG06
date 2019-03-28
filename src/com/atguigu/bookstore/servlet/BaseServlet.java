package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BaseServlet提取的步骤：
 * 	1、创建Servlet文件命名为BaseServlet
 * 	2、将UserServlet中的doGet和doPost剪切到BaseServlet中+  UserServlet继承BaseServlet
 * 	3、将doGet方法中的Class后的泛型去掉
 * 
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//this 代表 UserServlet的对象
		//this.getClass()  获取当前对象的类型
		//type 对应一个方法名
		//方法结构都和doGet一样，方法形参类型列表 都已知
		//反射：  1、根据type值和Class以及形参类型列表 在类中确定唯一的一个方法 2、通过this执行方法并传入 request和response参数
		Class cla = this.getClass();
		String type = request.getParameter("type");
		System.out.println("BaseServlet:  "+cla);
		System.out.println(type);
		try {
			//参数1： 方法名字符串    参数2.： 形参类型列表
			Method method = cla.getDeclaredMethod(type,HttpServletRequest.class  , HttpServletResponse.class);
			//参数1：调用方法的对象   参数2：  实参
			method.invoke(this, request ,response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
