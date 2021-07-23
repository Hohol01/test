<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 01.06.2021
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title>ADDING TEST</title>
</head>
<body>
<form method="post">
    <%@include file="/WEB-INF/jspf/language.jspf" %>
</form>
<center>
    <form action="addtest" method="post">
        <table>
            <tr>
                <td><fmt:message key="addtest_jsp.name_of_the_test"/> =</td>
                <td><input name="name" type="text" required></td>
            </tr>

            <tr>
                <td><fmt:message key="addtest_jsp.time"/> =</td>
                <td><input name="time" type="text" pattern="^[0-9]+"></td>
            </tr>
            <tr>
                <td><fmt:message key="addtest_jsp.hardness"/> =</td>
                <td><select name="hardnest" required>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select></td>
            </tr>
            <tr>
                <td><fmt:message key="addtest_jsp.subject"/></td>
                <td><select name="subdgect" required>
                    <option></option>
                    <c:forEach var="lang" items="${lang}">
                        <option value=<c:out value="${lang.id}"/>>
                        <c:out value="${lang.subject}"/></option>
                    </c:forEach>
                </select></td>
            </tr>
        </table>
        <c:if test="${requestScope.error != null}">
            <a>Заполните поля правильно</a><br>
        </c:if>
        <input type="submit" name="addtest" value='<fmt:message key="addtest_jsp.next"/>'>
    </form>
    <a href="home"><fmt:message key="addtest_jsp.home"/></a>
</center>


</body>
</html>
