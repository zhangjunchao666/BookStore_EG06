<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <c:if test="${!empty page.data }">
<div class="scott">
		<c:choose>
			<c:when test="${page.totalPage<=5 }">
				<c:set var="begin" value="1"></c:set>
				<c:set var="end" value="${page.totalPage }"></c:set>
			</c:when>
			<c:otherwise>
				<!-- 总页码>5 -->
				<c:choose>
					<c:when test="${page.pageNumber<=3 }">
						<!-- 如果当前页码<=3 -->
						<c:set var="begin" value="1"></c:set>
						<c:set var="end" value="5"></c:set>
					</c:when>
					<c:otherwise>
						<!-- 如果当前页码>3 -->
						<c:set var="begin" value="${page.pageNumber-2 }"></c:set>
						<c:set var="end" value="${page.pageNumber+2 }"></c:set>
						<!-- 判断end是否超过总页码，最大只能=totalPage -->
						<c:if	test="${end>page.totalPage }">
							<c:set var="end" value="${page.totalPage }"></c:set>
							<c:set var="begin" value="${end-4 }"></c:set>
						</c:if>
					</c:otherwise>
					
				</c:choose>
			</c:otherwise>
		</c:choose>
	
	
	
		<a href="${page.path }&pageNumber=${(page.pageNumber-1)>0?(page.pageNumber-1):1 }"> &lt; </a>
		<!-- 通过c:forEach遍历一组数生成页码 -->
		<c:forEach begin="${begin }" end="${end }" var="index">
			<!-- 判断 正在遍历的索引是否等于当前页码，如果是 则高亮显示 -->
			<c:choose>
				<c:when test="${index==page.pageNumber }">
					<!-- 当前页 -->
					<span class="current">${index }</span>
				</c:when>
				<c:otherwise>
					<a href="${page.path }&pageNumber=${index }">${index }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<a href="${page.path }&pageNumber=${(page.pageNumber+1)>page.totalPage?page.totalPage:(page.pageNumber+1) }"> &gt; </a>
	
	共${page.totalPage }页，${page.totalCount }条记录 到第<input
		value="${page.pageNumber }" name="pn" id="pn_input" />页 <input
		id="sendBtn" type="button" value="确定">
		
		<script type="text/javascript">
			//给确定按钮绑定单机事件，点击时跳转到 name="pn"页码对应的页面
			$("#sendBtn").click(function(){
				//获取用户输入的页码
				var pageNumber = $("#pn_input").val();
				//向BookManagerServlet.findPage方法发送请求  并发送pageNunmber参数
				//在js中使用EL表达式，必须在引号中使用
				window.location = "${page.path }&pageNumber="+pageNumber;
			});
		</script>
	</div>
</c:if>   