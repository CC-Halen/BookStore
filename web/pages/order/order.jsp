<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>


	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">我的订单</span>

		<%--静态包含，登录 成功之后的菜单 --%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>


	</div>
	
	<div id="main">
		
		<table>
			<c:if test="${not empty sessionScope.allOrders}">
				<tr>
					<th>日期</th>
					<th>金额</th>
					<th>状态</th>
					<th>详情</th>
				</tr>

				<c:forEach items="${sessionScope.allOrders}" var="order">
					<tr>
						<td>${order.create_time}</td>
						<td>${order.price}</td>
						<td>
							<c:if test="${order.status == 0}">
								未发货
							</c:if>
							<c:if test="${order.status == 1}">
								已发货
							</c:if>
							<c:if test="${order.status == 2}">
								已收货
							</c:if>
						</td>
						<td><a href="orderServlet/action?showOrderDetail&order_id=${order.order_id}&create_time=${order.create_time}">查看详情</a></td>
					</tr>
				</c:forEach>
			</c:if>

			<c:if test="${ empty sessionScope.allOrders}">
				<h2>你还没有任何订单，可以浏览商品加购哦。。</h2>
			</c:if>

		</table>
		
	
	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>