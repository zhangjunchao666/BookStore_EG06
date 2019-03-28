<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<!-- 引入base页面：包含了 base标签(到项目名)  、css样式 、jquery文件-->
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
		<!-- 被访问时  直接转发请求给BookClientServlet.findPage() -->
		<jsp:forward page="/BookClientServlet?type=findPage&pageNumber=1"></jsp:forward>
</body>
</html>