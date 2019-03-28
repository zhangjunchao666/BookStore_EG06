<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
<!-- 引入base页面：包含了 base标签(到项目名)  、css样式 、jquery文件-->
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
		</div>
		
		<div id="main">
			<form action="BookManagerServlet" method="post">
				<!-- 通过隐藏域携带方法名 -->
				<input type="hidden" name="type" value="editBook" />
				<!-- 通过隐藏域携带 修改之前的页面地址 -->
				<input type="hidden" name="ref" value="${requestScope.ref }" />
				
				<!-- 通过隐藏域携带图书id，服务器需要根据id修改其他属性 -->
				
				<input type="hidden" name="id" value="${book.id }" />
				<!-- 通过隐藏域给图书信息添加一个图书封面默认地址 -->
				<input type="hidden" name="imgPath" value="${book.imgPath }" />
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="title" type="text" value="${requestScope.book.title }"/></td>
						<td><input name="price" type="text" value="${book.price }"/></td>
						<td><input name="author" type="text" value="${book.author }"/></td>
						<td><input name="sales" type="text" value="${book.sales }"/></td>
						<td><input name="stock" type="text" value="${book.stock }"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>