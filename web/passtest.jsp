<%@ page import="entity.question" %><%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 07.06.2021
  Time: 01:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>PASSING</title>
</head>
<body>
<form action="passingtest" method="post">
<%
    int count=0;

%>

<c:forEach var="ques" items="${ques}">
  <c:out value="${ques.text}"/>
    <table border="1">
    <c:forEach var="ans" items="${ans}">
        <%
            count++;
        %>
        <tr>
            <td><c:out value="${ans.answer}"/></td>

            <td><input type="checkbox" name="${ques.number}"  value="${ans.id}" <%
                if (request.getAttribute("chec"+count)!=null){
                    out.print("checked =\"checked\"");
                }
            %> ></td>
        </tr>

    </c:forEach>

    </table>

        <%
            if (request.getAttribute("gotopre")!=null){
                out.print("<input type=\"submit\" name=\"previous\" value=\"предыдущей вопрос\">");
            }
            if(request.getAttribute("gotonext")!=null){
                out.print("<input type=\"submit\" name=\"next\" value=\"следующий вопрос\">");
            }
        %>


        <input type="submit" name="finish" value="закончить попытку">
    </form>
</c:forEach>
</body>
</html>
