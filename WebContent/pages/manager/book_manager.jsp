<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<!-- 引入base页面：包含了 base标签(到项目名)  、css样式 、jquery文件-->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		//删除a绑定单击事件，点击时给用户删除提示
		$(".delA").click(function(){
			//this代表被点击的删除a标签
			var title = $(this).parents("tr").children("td:first").text();
			//a标签的默认行为就是让服务器删除指定的图书
			if(!confirm("你真的要删除《"+ title +"》吗？")){
				//取消删除，取消默认行为即可
				return false;
			}
		});
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
	</div>
	
	<div id="main">
		<!-- 先判断域中的list集合是否为空 -->
		<!-- 修改：   判断 page对象的data集合是否为空 -->
		<c:choose>
			<c:when test="${empty requestScope.page.data }">
				<!-- 没有数据 -->
				<h2 style="color:red;text-align: center;margin-top: 120px;">生意太好了，图书卖空了......<a href="pages/manager/book_add.jsp">点击添加图书</a></h2>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<c:forEach items="${requestScope.page.data}" var="book">
						<tr>
							<td>${pageScope.book.title }</td>
							<td>${book.price }</td>
							<td>${book.author }</td>
							<td>${book.sales }</td>
							<td>${book.stock }</td>
							<td><a href="BookManagerServlet?type=findBook&bookId=${book.id }">修改</a></td>
							<td><a class="delA" href="BookManagerServlet?type=deleteBook&bookId=${book.id }">删除</a></td>
						</tr>	
					</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><a href="pages/manager/book_add.jsp">添加图书</a></td>
					</tr>	
				</table>
					
			</c:otherwise>
		</c:choose>
	</div>
	
	<!-- 通过include指令静态引入  分页导航栏 -->
	<%@ include file="/WEB-INF/include/nav.jsp" %>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>