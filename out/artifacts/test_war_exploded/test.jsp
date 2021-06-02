<%@ page import="db.DAOtest" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.admin" %>
<%@ page import="entity.test" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 01.06.2021
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="=1">
        <c:forEach var="test" items="${testlist}"  >

            <tr><td>${test.name}</td></tr>
        </c:forEach>
</table>

</body>
</html>
