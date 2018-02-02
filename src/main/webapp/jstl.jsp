<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/16 0016
  Time: 下午 5:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>jstl</title>
</head>
<body>
<table border="1">
    <th>遍历map</th>
    <c:forEach items="${map}" var="mapEntry">
        <tr>
            <td><c:out value="${mapEntry.key}"></c:out></td>
            <td><c:out value="${mapEntry.value}"></c:out></td>
        </tr>
    </c:forEach>
</table>
<table border="1">
    <th>遍历Cookie的map</th>
    <c:forEach items="${map1}" var="mapEntry">
        <tr>
            <td><c:out value="${mapEntry.key}"></c:out></td>
            <td><c:out value="${mapEntry.value}"></c:out></td>
        </tr>
    </c:forEach>
</table>
<hr color="red">
<hr color="red">
<hr color="red">
<hr color="red">
<hr color="red">
<table border="1">
    <th>遍历list</th>
    <c:forEach items="${list}" var="li">
        <tr>
            <td><c:out value="${li}"></c:out></td>
        </tr>
    </c:forEach>
</table>
<table border="1">
    <th>遍历Cookie的list</th>
    <c:forEach items="${list1}" var="li">
        <tr>
            <td><c:out value="${li}"></c:out></td>
        </tr>
    </c:forEach>
</table>
<hr color="red">
<hr color="red">
<hr color="red">
<hr color="red">
<h1 style="color: blue">
<fmt:formatNumber type="number" value="1000009.00" pattern="0.00" maxFractionDigits="2"/>
</h1>
<fmt:formatDate value="<%=new Date() %>"  dateStyle="full"/>
<hr color="red">
<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd HH:mm"/>
</body>
</html>
