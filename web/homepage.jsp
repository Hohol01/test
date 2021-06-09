<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 04.06.2021
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HOME PAGE</title>
</head>
<body>
<center>
<a href="test"> список тестов</a>
<a>
    <form action="home" method="post">

        <input type="submit" name="test" value="перейти к тестам" /><br>
        <%System.out.println(request.getSession().getAttribute("role"));
            if(request.getSession().getAttribute("role").equals("teacher") ){
                out.print("<input type=\"submit\" name=\"add\" value=\"добавить тест\"><br>");
                out.print("<input type=\"submit\" name=\"users\" value=\"пользователи\" /><br>");
                out.print("<input type=\"submit\" name=\"addusers\" value=\"добавить пользователя\" /><br>");
                out.print("<input type=\"submit\" name=\"editusers\" value=\"редактировать пользователя\" /><br>");
            }
            else
                out.print("<input type=\"submit\" name=\"results\" value=\"результаты тестов\"><br> ");
        %>
       <input type="submit" name="out" value="выйти" /><br>
    </form>
</a>

</center>
</body>
</html>
