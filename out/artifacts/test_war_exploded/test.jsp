
<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 01.06.2021
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <form name="t" method="post" >
<table border="1">
<tr>
    <td>название теста</td>
    <td>предмет</td>
    <td>сложность</td>
    <td>время</td>

</tr>

        <c:forEach var="tests" items="${tests}"  >
            <tr>
                <td>  <c:out value="${tests.name}"/></td>
                <td> <c:out value="${tests.subdgect}"/></td>
                <td><c:out value="${tests.hardnest}"/></td>
                <td><c:out value="${tests.time}"/></td>


                <td><input type="submit" name="${tests.id }" value="пройти"></td>
                <td><a href="edit?idtest=${tests.id} ">редактировать тест</a></td>
            </tr>
        </c:forEach>

</table>
    </form>
</center>
</body>
</html>
