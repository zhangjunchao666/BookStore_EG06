<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<!-- 引入base页面：包含了 base标签(到项目名)  、css样式 、jquery文件-->
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>订单编号</td>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>		
			<c:forEach items="${list }" var="order">
						<tr>
							<td>${order.id }</td>
							<td><fmt:formatDate value="${order.orderTime }" type="both" dateStyle="long" timeStyle="short"  /></td>
							<td>${order.totalAmount }</td>
							<td>
								<!-- 根据state的值显示不同的文案 -->
								<c:choose>
									<c:when test="${order.state==0 }"><a href="OrderManagerServlet?type=sendGoods&orderId=${order.id }">点击发货</a></c:when>
									<c:when test="${order.state==1 }">等待用户签收</c:when>
									<c:when test="${order.state==2 }">交易完成</c:when>
								</c:choose>
							</td>
							<td><a href="#">查看详情</a></td>
						</tr>
					</c:forEach>
		</table>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>