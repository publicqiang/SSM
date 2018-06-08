<%--
  Created by IntelliJ IDEA.
  User: 10408
  Date: 2018/6/8
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h4><a href="user/findList">查询所有用户</a></h4>

<form action="user/index" method="post">
    用户名：<input type="text" name="userName"/>
    密码：<input type="password" name="password"/>
    <input type="submit" value="注册"/><font color="red">${error}</font>
</form>

</body>
</html>
