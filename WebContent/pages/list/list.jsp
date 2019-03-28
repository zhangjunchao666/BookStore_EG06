<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<!-- 引入base页面：包含了 base标签(到项目名)  、css样式 、jquery文件-->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function() {
		
		//给查询按钮绑定单机事件， 点击时获取最低和最高价格并提交请求给BookClientServlet?type=findPageByPrice
		$("#quert_btn").click(function(){
			var minPrice = $("input[name='minPrice']").val();
			var maxPrice = $("input[name='maxPrice']").val();
			//修改地址栏地址 浏览器就会向新地址发请求
			window.location = "${pageContext.request.contextPath}/BookClientServlet?type=findPageByPrice&minPrice="+minPrice+"&maxPrice="+maxPrice;
			
		});
		
		//加入购物车 异步实现  当前购物车商品个数 以及  刚添加到购物车商品名的显示
		$(".addCart").click(function(){
			//获取要加入到购物车的商品的id 
			var id = this.id ;
			$.ajax({
				url:"CartServlet",
				data:{"type":"addBook","bookId":id},
				type:"POST",
				dataType:"json",
				success:function(result){  // 如果预期服务器返回的是Json格式的数据， JQuery会直接将数据转换成json对象
					alert(result.title + " , " + result.totalCount);
					
					$("#totalCount").html("您的购物车中有"+result.totalCount+"件商品");
					$("#title").html("您刚刚将<span style='color: red'>"+result.title+"</span>加入到了购物车中");
				}
			});
			
			return false ; 
			
		});
		
		
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
				价格：<input type="text" name="minPrice" value="${param.minPrice }"> 元 - <input type="text" name="maxPrice" value="${param.maxPrice }"> 元 <button id="quert_btn">查询</button>
			</div>
			<div style="text-align: center">
				<c:choose>
					<c:when test="${empty sessionScope.cart.cartItemList }">
						<span id="totalCount">您的购物车中还没有商品，快去添加吧！</span>
						<div id="title">
							<br/>
						</div>
					</c:when>
					<c:otherwise>
						<span id="totalCount">您的购物车中有${sessionScope.cart.totalCount }件商品</span>
						<div id="title">
							您刚刚将<span style="color: red">${sessionScope.title }</span>加入到了购物车中
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<!-- 遍历显示分页数据 -->
			<c:choose>
				<c:when test="${empty page.data }">
					<h2 style="color: red; text-align: center;margin-top: 50px; ">你来晚了一步，图书卖空了......</h2>
				</c:when>
				<c:otherwise>
					<c:forEach items="${page.data }" var="book">
						<div class="b_list">
							<div class="img_div">
								<img class="book_img" alt="" src="static/img/default.jpg" />
							</div>
							<div class="book_info">
								<div class="book_name">
								<span class="sp1">书名:</span>
								<span class="sp2">${book.title }</span>
								</div>
								<div class="book_author">
									<span class="sp1">作者:</span>
									<span class="sp2">${book.author }</span>
								</div>
								<div class="book_price">
									<span class="sp1">价格:</span>
									<span class="sp2">￥${book.price }</span>
								</div>
								<div class="book_sales">
									<span class="sp1">销量:</span>
									<span class="sp2">${book.sales }</span>
								</div>
								<div class="book_amount">
									<span class="sp1">库存:</span>
									<span class="sp2">${book.stock }</span>
								</div>
								<div class="book_add">
									<a  class="addCart"  id="${book.id }"  href="CartServlet?type=addBook&bookId=${book.id }">加入购物车</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
		
		</div>
		
	</div>
	
	
		<!-- 引入分页导航栏 -->
		<%@ include file="/WEB-INF/include/nav.jsp" %>
	
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>