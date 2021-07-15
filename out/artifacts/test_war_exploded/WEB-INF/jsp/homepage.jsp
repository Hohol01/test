<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 04.06.2021
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

<head>
    <title>HOME PAGE</title>
</head>
<body>

<%@include file="/WEB-INF/jspf/language.jspf"%>

<br>
<center>


    <form action="home" method="post">

        <input type="submit" name="test" value='<fmt:message key="homepaje_jsp.button.goToTests"/>' /><br>
        <a <%if(!request.getSession().getAttribute("role").equals("teacher") ){
                out.print("hidden");
            }%>>
            <input type="submit" name="add" value='<fmt:message key="homepaje_jsp.button.addtest"/>'><br>
            <input type="submit" name="users" value='<fmt:message key="homepaje_jsp.button.users"/> ' /><br>
            <input type="submit" name="addusers" value='<fmt:message key="homepaje_jsp.button.addUser"/> ' /><br>
        </a>
            <a <%if(!request.getSession().getAttribute("role").equals("student") ){
                out.print("hidden");
            }%>><input type="submit" name="results" value='<fmt:message key="homepaje_jsp.button.resultTests"/>'><br> </a>

       <input type="submit" name="out" value='<fmt:message key="homepaje_jsp.button.exit"/>' /><br>
    </form>


</center>

</body>
</html>
