<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 07.06.2021
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

%>

<form action="edit" method="post">
    <c:forEach var="ques" items="${ques}">
<a>измените вопрос</a><input type="text" name="question" value="${ques.text}"><br>

    <c:forEach var="ans" items="${ans}">
    <a>измените ответ</a><input type="text" name="ans1" value="${ans.answer}" > <input type="checkbox" name="correct1"><br>

    </c:forEach>
    </c:forEach>
    <input type="submit" name="next" value="изменить вопрос">
    <input type="submit" name="finish" value="закончить изменение теста">
</form>


<form action="edit" method="post">
    <c:forEach var="test" items="${test}">
        <a>название теста = <input name="name" type="text" value="${test.name}"></a><br>
        <c>ввремя в минутах = <input name="time" type="int" value="${test.time}"> </c><br>
        <d>cложность = <input name="hardnest" type="int" value="${test.hardnest}"> </d> <br>
        <d>предмет = <input name="subdgect" type="text" value="${test.subdgect}"> </d> <br>
        <input type="submit" name="addtest" value="далее">
    </c:forEach>
</form>
</body>
</html>
