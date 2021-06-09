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
    <title>Title</title>
</head>
<body>
<form method="post" action="adduser">
<center>

  <table border="1">

    <tr>
      <td>Фамилия</td>
      <td><input type="text" name="surname"></td>
    </tr>

    <tr>
      <td>Имя</td>
      <td><input type="text" name="name"></td>
    </tr>


    <tr>
      <td>Отчество</td>
      <td><input type="text" name="patronymic"></td>
    </tr>
    <tr>
      <td>выберете роль пользователя</td>
      <td><select name="role" >
        <option></option>
        <option value="teacher"> админ</option>
        <option value="student">студент</option>
      </select> </td>
    </tr>

    <tr>
      <td>Логин пользователя</td>
      <td><input type="text" name="login"></td>
    </tr>

    <tr>
      <td>Пароль пользователя</td>
      <td><input type="text" name="password"></td>
    </tr>



  </table>
  <input type="submit" name="add" value="добавить пользователя">
</center>
</form>
</body>
</html>
