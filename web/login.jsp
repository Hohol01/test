<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 14.05.2021
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>LOGIN</title>
    </head>
    <body>

        <center>
            <h1>login</h1>
          <form  action="login" method="post" >
                 <table>
                    <tr><td>User:<input name="login" type="text" size="10"></td></tr>
                   <tr><td>Password:<input name="pass" type="password" size="10"></td></tr>
                     <tr><td> <input type="submit" name="go"></td></tr>
                 </table>
            </form>
        </center>
    </body>
</html>
