<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 01.06.2021
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addtest</title>
</head>
<body>
<center>
    <form action="addtest" method="post">
        <table>
            <tr>
                <td> название теста =</td>
                <td> <input name="name" type="text"></td>
            </tr>

            <tr>
                <td>ввремя в минутах = </td>
                <td><input name="time" type="int"></td>
            </tr>
            <tr>
                <td>cложность =</td>
                <td><select name="hardnest">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select></td>
            </tr>
            <tr>
                <td> предмет =</td>
                <td> <input name="subdgect" type="text"> </td>
            </tr>
        </table>
        <input type="submit" name="addtest" value="далее">
</form>
    <a href="home">домой</a>
</center>


</body>
</html>
