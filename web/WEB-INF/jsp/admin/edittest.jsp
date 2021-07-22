<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 07.06.2021
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EDIT TEST</title>
</head>
<body>
<center>

    <form method="post">
        <c:forEach var="test" items="${test}">
            <a>название теста = <input name="name" type="text" value="${test.name}"></a><br>
            <c>ввремя в минутах = <input name="time" type="int" value="${test.time}"></c>
            <br>
            <d>cложность = <input name="hardnest" type="int" value="${test.hardnest}"></d>
            <br>
            <d>предмет =
                <td><select name="subdgect" required>
                    <c:forEach var="lang" items="${lang}">
                        <c:if test="${test.subdgect==lang.subject}">
                        <option value=<c:out value="${lang.id}"/>> <c:out value="${lang.subject}"/></option>
                        </c:if>
                    </c:forEach>
                    <c:forEach var="lang" items="${lang}">
                        <c:if test="${test.subdgect!=lang.subject}">
                            <option value=<c:out value="${lang.id}"/>> <c:out value="${lang.subject}"/></option>
                        </c:if>
                    </c:forEach>
                </select></td>
            </d>
            <br>
            <input type="submit" name="next" value="далее">
            <input type="submit" name="delete" value="удалить">
        </c:forEach>
    </form>
</center>
</body>
</html>
