<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
    <!-- 引入fmt标签库 -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<!-- 引入base页面：包含了 base标签(到项目名)  、css样式 、jquery文件-->
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
h1 {
	text-align: center;
	margin-top: 200px;
}
</style>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">我的订单</span>
		<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>

	<div id="main">
		<c:choose>
			<c:when test="${empty list }">
				<h2>您还没有订单，快去购买吧！！！</h2>
			
			</c:when>
			<c:otherwise>
					<table>
					<tr>
						<td>订单编号</td>
						<td>日期</td>
						<td>金额</td>
						<td>状态</td>
						<td>详情</td>
					</tr>
					<c:forEach items="${list }" var="order">
						<tr>
							<td>${order.id }</td>
							<!-- 
							fmt:formatDate的属性
								1、type属性：  
									both：显示全部
									date：显示年月日
									time：显示时分秒
								2、dateStyle属性：
									full: 
									long:
									short:
								3、timeStyle属性：
									full/long/short
							
							 -->
							<td><fmt:formatDate value="${order.orderTime }" type="both" dateStyle="long" timeStyle="short"  /></td>
							<td>${order.totalAmount }</td>
							<td>
								<!-- 根据state的值显示不同的文案 -->
								<c:choose>
									<c:when test="${order.state==0 }">未发货</c:when>
									<c:when test="${order.state==1 }"><a href="OrderClientServlet?type=takeGoods&orderId=${order.id }">确认收货</a></c:when>
									<c:when test="${order.state==2 }">交易完成</c:when>
								</c:choose>
							</td>
							<td><a href="#">查看详情</a></td>
						</tr>
					</c:forEach>
				</table>
				
			</c:otherwise>
		</c:choose>
	

	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2018 </span>
	</div>
</body>
</html>