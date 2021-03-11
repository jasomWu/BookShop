<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>

	<%--静态包含 base标签，css样式，jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

		<!--静态包含 管理模块的菜单-->
		<%@ include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>用户名</td>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>状态</td>
			</tr>
			<c:forEach items="${requestScope.AllOrders}" var="AllOrders">
				<tr>
					<td>${sessionScope.user.username}</td>
					<td>${AllOrders.orderId}</td>
					<td>${AllOrders.createTime}</td>
					<td>${AllOrders.price}</td>
					<td><a href="#">查看详情</a></td>
					<td><a href="#">点击发货</a></td>
				</tr>
			</c:forEach>


			

		</table>
	</div>

	<!--静态包含 页脚信息 -->
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>