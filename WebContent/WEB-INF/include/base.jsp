<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入核心标签库 -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- 动态拼接base标签中的url地址 -->
<%--   协议： <%=request.getScheme() %>
 主机地址：<%=request.getServerName() %>
 端口号：<%=request.getServerPort() %>
 上下文路径： <%=request.getContextPath() %>  --%>  
<%-- <base href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/"/> --%>
<base href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
<!-- <base href="http://localhost:8080/BookStore_EG03/"/> -->
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >