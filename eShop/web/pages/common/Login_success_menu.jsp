<%--
  Created by IntelliJ IDEA.
  User: sunnu
  Date: 2021/2/3
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <div>
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临简易书城</span>
            <a href="orderServlet?action=showMyOrders">我的订单</a>
            <a href="userServlet?action=loginOut">注销</a>&nbsp;&nbsp;
            <a href="index.jsp">返回</a>
        </div>

