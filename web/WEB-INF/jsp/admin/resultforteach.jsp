<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 08.06.2021
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>RESULT</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/language.jspf" %>
<center>
    <table border="1">
        <tr>
            <td><fmt:message key="resultforteach_jsp.surname"/></td>
            <td> <fmt:message key="resultforteach_jsp.name"/></td>
            <td><fmt:message key="resultforteach_jsp.patronymic"/></td>
            <td><fmt:message key="resultforteach_jsp.marc"/></td>
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

        <input type="submit" name="back" value='<fmt:message key="resultforteach_jsp.button.back"/>'>
    </form>
</center>
</body>
</html>
