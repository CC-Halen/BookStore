<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>


</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

		<%-- 静态包含 manager管理模块的菜单  --%>
		<%@include file="/pages/common/manager_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>

			<c:if test="${not empty sessionScope.orderItems}">
				<tr>
					<th>日期</th>
					<th>金额</th>
					<th>详情</th>
					<th>发货</th>

				</tr>
				<c:forEach items="${sessionScope.orderItems}" var="orderItem">
					<tr>
						<td>${sessionScope.create_time}</td>
						<td>${orderItem.total_price}</td>
						<td><a href="#">查看详情</a></td>
						<td><a href="orderServlet/action=sendOrder&order_id=${sessionScope.order_id}">点击发货</a></td>
					</tr>
				</c:forEach>


			</c:if>

		</table>
	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>