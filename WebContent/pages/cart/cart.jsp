<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<!-- 引入base页面：包含了 base标签(到项目名)  、css样式 、jquery文件-->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		//给显示购物项数量的表单项绑定内容改变的监听：当表单项value值改变了并且失去了焦点才会触发此事件
		$(".countInp").change(function(){
			//alert(this.value);
			//向服务器发起请求 修改购物项的数量
			var count = this.value;
			//获取要修改的图书id
			var bookId = this.name;//使用标签的属性动态的携带标签对应的图书数据
			window.location = "CartServlet?type=updateCount&bookId="+bookId+"&count="+count;			
		});
		
		
		$(".delA").click(function(){
			//给用户删除提示
			var title = $(this).parents("tr").children("td:first").text();
			if(!confirm("你真的要删除 《"+title+"》 吗？")){
				//用户选择取消删除时 只要 取消标签的默认行为即可
				return false;
			}
		});
		
		
		//作业: 通过异步的方式实现购物车商品数量的修改.
		
		
	});

</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		<!-- 判断购物车中是否有数据 -->
		<c:choose>
			<c:when test="${empty cart.cartItemList }">
				<h2 style="color:red;text-align: center;margin-top: 80px;">空空如也，快去添加吧！！</h2>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>商品名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>金额</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${cart.cartItemList }" var="cartItem">
						<tr>
							<td>${cartItem.book.title }</td>
							<td><input name="${cartItem.book.id }" class="countInp" value="${cartItem.count }" style="width:40px;text-align: center;" /></td>
							<td>${cartItem.book.price }</td>
							<td>${cartItem.amount }</td>
							<td><a class="delA"  href="CartServlet?type=deleteCartItem&bookId=${cartItem.book.id }">删除</a></td>
						</tr>
					</c:forEach>		
				</table>
				
				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count">${cart.totalCount }</span>件商品</span>
					<span class="cart_span">总金额<span class="b_price">${cart.totalAmount }</span>元</span>
					<span class="cart_span"><a href="CartServlet?type=clearCart">清空购物车</a></span>
					<span class="cart_span"><a href="OrderClientServlet?type=checkOut">去结账</a></span>
				</div>
			
			</c:otherwise>
		</c:choose>
		
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>