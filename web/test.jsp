
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

    <samp></samp>
    <form method="post">
        <a>найти по названию<input type="text" name="search" ></a>
        <input type="submit" name="search1" value="search">
<table border="1">

<tr>

    <td><input type="submit" name="sort" value="название теста"></td>
    <td>предмет:<select name="subject">
        <option>все</option>
        <c:forEach  var="list" items="${list}"  >

             <option value="${list.subdgect}"> ${list.subdgect}</option>
        </c:forEach>
    </select><input type="submit" name="select" value="выбрать" ></td>
    <td><input type="submit" name="sort" value="сложность"></td>
    <td><input type="submit" name="sort" value="время"></td>

</tr>

        <c:forEach var="tests" items="${tests}"  >
            <tr>
                <td>  <c:out value="${tests.name}"/></td>
                <td> <c:out value="${tests.subdgect}"/></td>
                <td><c:out value="${tests.hardnest}"/></td>
                <td><c:out value="${tests.time}"/></td>



                <td <% if(request.getSession().getAttribute("role").equals("teacher"))
                    out.print("hidden");%> > <a href="passingtest?idtest=${tests.id}">пройти тест</a></td>

                <td <% if(request.getSession().getAttribute("role").equals("student"))
                    out.print("hidden");%> ><a href="edit?idtest=${tests.id}">редактировать тест</a></td>
                <td <% if(request.getSession().getAttribute("role").equals("student"))
                    out.print("hidden");%> ><a href="results?idtest=${tests.id}">результаты теста</a></td>
            </tr>
        </c:forEach>

</table>
    </form>
    <a href="home">домой</a>
</center>
</body>
</html>
