<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 09.06.2021
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EDIT USER</title>
</head>
<body>

<form method="post">
<table border="1">
    <tr>
        <td>Имя</td>
        <td>Фамилия</td>
        <td>Отчество</td>
    </tr>

<c:forEach items="${user}" var="user">
    <tr>
        <td><c:out value="${user.name}"/></td>
        <td><c:out value="${user.surname}"/></td>
        <td><c:out value="${user.patronymic}"/></td>

        <td><a href="edituser?userid=${user.id}">редактировать пользователя</a></td>
        <td><input type="submit" name="${user.id}" value="удалить пользователя" ></td>

    </tr>
</c:forEach>
</table>
    <input type="submit" name="back" value="назад">
</form>
</body>
</html>
