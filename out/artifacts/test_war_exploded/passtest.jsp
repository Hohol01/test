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
    <script type="text/javascript">
        var counter =<%=request.getAttribute("time")%>;


        function time(){
            counter --;

            document.getElementById("count").innerText = "осталось "+(counter-counter%60)/60+":"+ counter%60;
            if (counter!=0){
                setTimeout("time()" ,1000);
            }else {
                document.getElementById("count").innerText = "время вышло нажмите завершить  ";
            }

        }


    </script>
</head>
<body  onload="time()">
<center>
<samp id="count"></samp>
<form action="passingtest" method="post">
<%
    int count=1;

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
                if (request.getAttribute("${ques.number}")!=null){
                    out.print("checked =\"checked\"");
                }
                count++;
            %> ></td>
        </tr>

    </c:forEach>

    </table>
    <input  <% if (request.getAttribute("gotopre")==null)
                out.print("hidden ");
            %>  type="submit" name="previous" value="предыдущей вопрос">



    <input <% if (request.getAttribute("gotonext")==null)
                out.print("hidden ");
            %> type="submit" name="next" value="следующий вопрос">

    <input type="submit" name="finish" value="закончить попытку">
    </form>
</c:forEach>
</center>
</body>
</html>
