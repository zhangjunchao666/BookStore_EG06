<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<!-- 引入base页面：包含了 base标签(到项目名)  、css样式 、jquery文件-->
<%@ include file="/WEB-INF/include/base.jsp" %>
<!-- 使用js  结合正则表达式 在注册信息提交之前对字符串的规则进行验证 -->
<script type="text/javascript">
	$(function(){
		var i = 1;
		//给验证码图片绑定单击事件，点击切换验证码图片
		$("#codeImg").click(function(){
			//只要重新访问一次KaptchaServlet就可以得到一张新的图片，再设置给src属性
			//谷歌浏览器每次点击时不使用缓存，直接向服务器发请求可以得到新的图片设置到img中
			//但是IE和火狐浏览器认为属性值都是一样的，没有必要向服务器发请求，直接使用图片缓存设置到img中[没有改变]
			//解决： 只要每次请求的地址不同就可以解决,在地址后拼接变化的参数即可
			this.src = "code.jpg?t="+(i++);
		});
		//1、查找页面中的提交按钮 绑定单击事件  [点击时 获取用户输入的注册信息进行验证]
		$("#sub_btn").click(function(){
			//获取账号
			var username = $("input[name='username']").val();
			//获取密码
			var password = $("input[name='password']").val();
			var repwd = $("input[name='repwd']").val();
			//获取邮箱
			var email = $("input[name='email']").val();
			//使用正则对字符串规则进行验证   js直接支持正则对象,一个对象对应一个规则     /正则规则/
			//2、创建验证用户名的正则对象
			var unameReg = /^[a-zA-Z0-9_-]{3,16}$/;
			//验证用户名字符串      正则对象提供了reg.test(str)  如果传入的字符串满足规则 返回true ， 不满足返回false
			if(!unameReg.test(username)){
				//给用户提示  并阻止表单数据提交[取消提交按钮的默认行为]
				alert("请输入由大小写字母、数字、_或-组成的3~16位的用户名");
				return false;
			}
			//3、创建验证密码的正则对象
			var pwdReg = /^[a-z0-9_-]{6,18}$/;
			if(!pwdReg.test(password)){
				alert("请输入由小写字母、数字、_或-组成的6~18位的用户名");
				return false;
			}
			//重复密码必须和密码一样
			if(repwd!=password){
				alert("两次密码不一致");
				return false;
			}
			//4、创建验证邮箱的正则对象
			var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!emailReg.test(email)){
				alert("邮箱格式错误!!");
				return false;
			}
		});
		
		
		
		//异步校验用户名
		$("[name='username']").change(function(){
			var username  = this.value ; 
			
			$.ajax({
				url:"UserServlet",
				data:{"type":"checkUsername","username":username},
				type:"POST",
				dataType:"text",
				success:function(result){
					alert(result);
					// 0: 不能用     1:能用 
					if(result == 0 ){
						//不能用
						$(".errorMsg").html("用户名已被占用");
						$("#sub_btn").attr("disabled",true);
						$("#sub_btn").css("background-color","gray");
					}else{
						$(".errorMsg").html("用户名可以使用");
						$("#sub_btn").attr("disabled",false);
						$("#sub_btn").css("background-color","#39987c");
					}
				}
			});
			
		});
		
	});

</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>

</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<!-- 取出错误消息显示
										如果取出的为空，不能直接显示，可以显示一个空字符串
								 -->
								 <%-- <%
								 	String errorMsg = (String)request.getAttribute("errorMsg");
								 	if(errorMsg==null){
								 		errorMsg = "";
								 	}
								 
								 %>
								<span class="errorMsg"><%=errorMsg %></span> --%>
								<span class="errorMsg">${errorMsg }</span>
							</div>
							<div class="form">
								<!-- 浏览器解析的绝对路径 -->
								<form action="UserServlet" method="post">
									<input type="hidden" name="type" value="regist" />
									<label>用户名称：</label>
									<%-- <%
										String username = request.getParameter("username");
										if(username==null){
											username="";
										}
									%> --%>
									<!-- 回显注册失败的用户名信息 -->
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username"
										value="${param.username }"
									 />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email"
										value="${param.email }"
									 />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code"/>
									<img id="codeImg" alt="" src="code.jpg" style="float: right; margin-right: 40px;width: 80px;height: 40px;">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
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