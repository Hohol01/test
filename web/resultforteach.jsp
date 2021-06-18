<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 08.06.2021
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

<center>
<table border="1">
    <tr>
        <td>Фамилия</td>
        <td>Имя</td>
        <td>Отчество</td>
        <td>Оценка</td>
    </tr>
    <c:forEach var="res" items="${res}">
        <tr>
            <td><c:out value="${res.surname}"/></td>
            <td><c:out value="${res.nameuser}"/></td>
            <td><c:out value="${res.patronymic}"/></td>
            <td><c:out value="${res.mark}"/></td>
        </tr>
    </c:forEach>
</table>
<form action="results" method="post">

    <input type="submit" name="back" value="назад">
</form>
</center>
</body>
</html>
