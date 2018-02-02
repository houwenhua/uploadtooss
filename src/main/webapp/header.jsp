<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/16 0016
  Time: 上午 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>显示cookie信息</title>
</head>
<body>
<table border="1">
    <c:forEach items="${map}" var="mapEntry">
    <tr>
        <td><c:out value="${mapEntry.key}"></c:out></td>
        <td><c:out value="${mapEntry.value}"></c:out></td>
    </tr>
    </c:forEach>
</table>

</body>
</html>
