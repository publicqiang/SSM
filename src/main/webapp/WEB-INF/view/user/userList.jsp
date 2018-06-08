<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 10408
  Date: 2018/6/8
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>

<table>
    <thead>
    <tr>
        <th>序号</th>
        <th>用户名</th>
        <th>密码</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user" varStatus="status">
        <tr>
            <td>${status.index +1}</td>
            <td>${user.userName}</td>
            <td>${user.password}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
