<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>

	<%--静态包含 base标签，css样式，jQuery文件--%>
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
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>

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
				<td>状态</td>
				<td>签收订单</td>
			</tr>
			<c:forEach items="${requestScope.orders}" var="orders">

				<tr>
					<td>${sessionScope.user.username}</td>
					<td>${orders.orderId}</td>
					<td>${orders.createTime}</td>
					<td>${orders.price}</td>
					<td>${orders.status==0?"未发货":orders.status==1?"已发货":"已签收"}</td>
					<td><a href="#">确认收货</a></td>
				</tr>

			</c:forEach>


			

		</table>
		
	
	</div>

	<!--静态包含 页脚信息 -->
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>