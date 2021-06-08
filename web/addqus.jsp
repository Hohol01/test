<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADDING TEST</title>

</head>
<body>
<form method="post" action="addtest">
    <a>введите вопрос</a><input type="text" name="question" ><br>
    <a>введите ответ</a><input type="text" name="ans1" > <input type="checkbox" name="correct1"><br>
    <a>введите ответ</a><input type="text" name="ans2" > <input type="checkbox" name="correct2"><br>
    <%
        int count = 2;
        if (request.getParameter("addanswer")!=null){
            out.print("<a>введите ответ</a><input type=\"text\" name=\"ans" + count + "\" > <input type=\"checkbox\" name=\"correct" + count + "\"><br>");
            count++;

        }
    %>
    <input type="submit" name="add" value="добавить вопрос">
    <input type="submit" name="finish" value="закончить добавление теста">
</form>
<form method="get">
<input type="submit" name="addanswer" value="добавить вариант ответа">
</form>
</body>
</html>
