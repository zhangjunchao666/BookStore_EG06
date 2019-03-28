<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>
<!-- 引入base页面：包含了 base标签(到项目名)  、css样式 、jquery文件-->
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<!-- 显示错误消息的span
										如果正常打开login页面，显示 请输入用户名和密码
										如果是登录失败转发回来，显示 失败消息
									获取request域中的错误消息，如果没有证明第一次打开，如果有则是登录失败跳转回来的
								 -->
								<%-- <%
									String errorMsg = (String)request.getAttribute("errorMsg");
									if(errorMsg==null){
										errorMsg = "请输入用户名和密码";
									}
								%> 
								<span class="errorMsg"><%=errorMsg %></span> --%>
								<%-- <span class="errorMsg"><%=request.getAttribute("errorMsg")==null?"请输入用户名和密码":request.getAttribute("errorMsg") %></span> --%>
								<span class="errorMsg">${empty errorMsg?"请输入用户名和密码":errorMsg }</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<!-- 通过隐藏域 携带参数： 用户不可见 又可以提交给服务器 -->
									<input type="hidden" name="type" value="login"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>