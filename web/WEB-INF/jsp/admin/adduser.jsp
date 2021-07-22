<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 09.06.2021
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADDING USER</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/language.jspf"%>
<form method="post" action="adduser">
<center>

  <table border="1">

    <tr>
      <td><fmt:message key="adduser_jsp.surname"/></td>
      <td><input type="text" name="surname" required></td>
    </tr>

    <tr>
      <td><fmt:message key="adduser_jsp.name"/></td>
      <td><input type="text" name="name" required></td>
    </tr>


    <tr>
      <td><fmt:message key="adduser_jsp.patronymic"/> </td>
      <td><input type="text" name="patronymic" required></td>
    </tr>
    <tr>
      <td><fmt:message key="adduser_jsp.choose_role"/></td>
      <td><select name="role" required>
        <option></option>
        <option value="teacher"> <fmt:message key="adduser_jsp.role.admin"/> </option>
        <option value="student"><fmt:message key="adduser_jsp.role.user"/></option>
      </select> </td>
    </tr>

    <tr>
      <td><fmt:message key="adduser_jsp.login"/></td>
      <td><input type="text" name="login" required></td>
    </tr>

    <tr>
      <td><fmt:message key="adduser_jsp.password"/></td>
      <td><input type="text" name="password" required></td>
    </tr>



  </table>

  <input type="submit" name="add" value='<fmt:message key="adduser_jsp.add_user"/>'><br>
  <a href="home"><fmt:message key="adduser_jsp.home"/> </a>
</center>
</form>
</body>
</html>
