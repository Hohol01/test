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

<link rel="stylesheet" type="text/css" media="screen" href="style/st4.css"/>

<head>
    <title>RESULT</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/language.jspf" %>
<center>
    <table border="1">
        <tr>
            <td><fmt:message key="results_jsp.test_name"/> </td>
            <td><fmt:message key="results_jsp.marc"/></td>
        </tr>
        <c:forEach var="res" items="${res}">
            <tr>
                <td><c:out value="${res.name}"/></td>
                <td><c:out value="${res.mark}"/></td>
            </tr>
        </c:forEach>
    </table>
    <%@include file="/WEB-INF/jspf/pagination.jspf"%>
        
<!--
    <div class="pagination">
        <a href="#">&laquo;</a>
        <a href="?page=1">1</a>
        <a class="active" href="?page=2">2</a>
        <a href="?page=3">3</a>
        <a href="?page=4">4</a>
        <a href="?page=5">5</a>
        <a href="?page=6">6</a>
        <a href="?page=7">&raquo;</a>
    </div>
-->
    <form action="results" method="post">

        <input type="submit" name="back" value="назад">

    </form>
</center>
</body>
</html>
