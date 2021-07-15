<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 14.05.2021
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>


<head>
    <title>LOGIN</title>
</head>
<body>
<div id="topNavLine">здесь содержимое твоей полоски.</div>
    <%@ include file="/WEB-INF/jspf/language.jspf"%>
<center>
    <h1>login</h1>
    <form action="login" method="post">
        <table >
            <tr>
                <td><fmt:message key="login_jsp.user"/> :<input name="login" type="text" required></td>
            </tr>
            <tr>
                <td length="10"><fmt:message key="login_jsp.password"/>:<input name="pass" type="password" required></td>
            </tr>

        </table>
        <input type="submit" name="go" value='<fmt:message key="login_jsp.button.singIn"/>' ><br>

        <a <%
            if (request.getAttribute("error") == null)
                out.print("hidden");
        %>> <fmt:message key="login_jsp.errorPass"/> </a>


        <a <%
            if (session.getAttribute("block") == null)
                out.print("hidden");
        %>><fmt:message key="login_jsp.errorBlock"/> </a>
    </form>
</center>
</body>
</html>
