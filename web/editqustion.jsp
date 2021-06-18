<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 16.06.2021
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EDIT TEST</title>
</head>
<body>
<%
  int count = 1;
%>
<form  method="post">
    <c:forEach var="ques" items="${ques}">
      <input hidden name="id" value="${ques.id}">
      <a>измените вопрос</a><input type="text" name="question" value="${ques.text}"><br>
    <c:forEach var="ans" items="${ans}">
      <a>измените ответ</a><input type="text" name="ans<%out.print(count);%>" value="${ans.answer}" ><br>
      <input type="checkbox" name="correct<%out.print(count); count++;%>"><br>

    </c:forEach>
  </c:forEach>
  <%
    request.removeAttribute("count");
    request.setAttribute("count", count);
  %>

  <input <% if (request.getAttribute("next")==null)
                out.print("hidden ");
%> type="submit" name="edit" value="изменить и перейти к следующему вопросу">
  <input type="submit" name="finish" value="изменить и закончить изменение теста">

</form>



</body>
</html>
