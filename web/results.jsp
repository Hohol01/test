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
    <title>RESULT</title>
</head>
<body>


<table border="1">
    <tr>
        <td>название теста</td>
        <td>оценка</td>
    </tr>
<c:forEach var="res" items="${res}">
    <tr>
        <td><c:out value="${res.name}"/></td>
        <td><c:out value="${res.mark}"/></td>
    </tr>
</c:forEach>
</table>
<form action="results" method="post">

<input type="submit" name="back" value="назад">
</form>
</body>
</html>
