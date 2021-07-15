
<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 01.06.2021
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>


<html>
<head>
    <title>TESTS</title>
</head>
<body>
<form method="post"><%@include file="/WEB-INF/jspf/language.jspf"%></form>


<center>

    <samp></samp>
    <form method="post">
        <a><fmt:message key="test_jsp.find_by_name"/> <input type="text" name="search" ></a>
        <input type="submit" name="search1" value='<fmt:message key="test_jsp.search"/>'>
<table border="1">

<tr>

    <td><input type="submit" name="sort" value='<fmt:message key="test_jsp.test_name"/> '></td>
    <td><fmt:message key="test_jsp.subject"/>: <select name="subject">
        <option><fmt:message key="test_jsp.all"/></option>
        <c:forEach  var="list" items="${list}"  >

             <option value="${list.subdgect}"> ${list.subdgect}</option>
        </c:forEach>
    </select><input type="submit" name="select" value='<fmt:message key="test_jsp.choose"/>' ></td>
    <td><input type="submit" name="sort" value='<fmt:message key="test_jsp.hardness"/>'></td>
    <td><input type="submit" name="sort" value='<fmt:message key="test_jsp.time"/>'></td>

</tr>

        <c:forEach var="tests" items="${tests}"  >
            <tr>
                <td> <c:out value="${tests.name}"/></td>
                <td> <c:out value="${tests.subdgect}"/></td>
                <td><c:out value="${tests.hardnest}"/></td>
                <td><c:out value="${tests.time}"/></td>



                <td <% if(request.getSession().getAttribute("role").equals("teacher"))
                    out.print("hidden");%> > <a href="passingtest?idtest=${tests.id}"><fmt:message key="test_jsp.pass_test"/> </a></td>

                <td <% if(request.getSession().getAttribute("role").equals("student"))
                    out.print("hidden");%> ><a href="edit?idtest=${tests.id}"><fmt:message key="test_jsp.edit_test"/> </a></td>
                <td <% if(request.getSession().getAttribute("role").equals("student"))
                    out.print("hidden");%> ><a href="results?idtest=${tests.id}"><fmt:message key="test_jsp.results"/> </a></td>
            </tr>
        </c:forEach>

</table>
        <%@include file="/WEB-INF/jspf/pagination.jspf"%>
    </form>
    <a href="home"><fmt:message key="test_jsp.home"/> </a>
</center>
</body>
</html>
