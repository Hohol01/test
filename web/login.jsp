<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 14.05.2021
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html xmlns:f="http://java.sun.com/jsf/core"
xmlns:h="http://java.sun.com/jsf/html">
<f:loadBundle basemame="resources_en" var="msg"/>
<head>
    <title>LOGIN</title>
</head>
<body>
<span style="float: right">
    <a href="?lang=en">en</a>
    |
    <a href="?lang=de">ru</a>
</span>

<center>
    <h1>login</h1>
    <form action="login" method="post">
        <table>
            <tr>
                <td>User:<input name="login" type="text" required></td>
            </tr>
            <tr>
                <td>Password:<input name="pass" type="password" required></td>
            </tr>

        </table>
        <input type="submit" name="go" value="войти"><br>
        <%
            if (request.getAttribute("error") != null)
                out.print("неверный логин или пароль");
        %>

        <%
            if (session.getAttribute("block") != null)
                out.print("вы заблокированы");
        %>
    </form>
</center>
</body>
</html>
