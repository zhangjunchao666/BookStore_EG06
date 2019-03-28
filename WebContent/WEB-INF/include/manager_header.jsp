<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div>
		<!-- 访问BaseServlet的子类时 必须携带type参数 告诉父类管理的子类的方法 -->
		<a href="BookManagerServlet?type=findPage&pageNumber=1">图书管理</a>
		<a href="OrderManagerServlet?type=findAllOrders">订单管理</a>
		<a href="index.jsp">返回商城</a>
	</div>
