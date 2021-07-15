<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 13.06.2021
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EDIT USER</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/language.jspf"%>
<center>
    <form method="post">

        <c:forEach var="user" items="${user}">

            <table border="1">

                <tr>
                    <td><fmt:message key="adduser_jsp.surname"/></td>
                    <td><input type="text" name="surname" value="${user.surname}"></td>
                </tr>

                <tr>
                    <td><fmt:message key="adduser_jsp.name"/></td>
                    <td><input type="text" name="name" value="${user.name}"></td>
                </tr>


                <tr>
                    <td><fmt:message key="adduser_jsp.patronymic"/> </td>
                    <td><input type="text" name="patronymic" value="${user.patronymic}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="adduser_jsp.choose_role"/></td>
                    <td><select name="role">
                        <option value="student"><fmt:message key="adduser_jsp.role.user"/></option>
                        <option value="teacher"><fmt:message key="adduser_jsp.role.admin"/></option>
                    </select></td>
                </tr>

                <tr>
                    <td><fmt:message key="adduser_jsp.login"/></td>
                    <td><input type="text" name="login" value="${user.login}"></td>
                </tr>

                <tr>
                    <td><fmt:message key="adduser_jsp.password"/></td>
                    <td><input type="text" name="password" value="${user.password}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="adduser_jsp.block"/></td>
                    <td><input type="checkbox" name="block"   <%
                    if (request.getAttribute("block")!=null){
                        out.print("checked");
                    }
                %>></td>
                </tr>

            </table>
        </c:forEach>
        <input type="submit" name="add" value="изменить пользователя"><br>


    </form>
    <a href="home"><fmt:message key="adduser_jsp.home"/></a>
</center>
</body>
</html>