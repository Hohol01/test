<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>ADDING TEST</title>

</head>
<body>
<%@include file="/WEB-INF/jspf/language.jspf"%>
<form method="post" action="addtest">
    <a><fmt:message key="addqus_jsp.question"/> </a><input type="text" name="question" required><br>
    <a><fmt:message key="addqus_jsp.answer"/> </a><input type="text" name="ans1" value=""> <input type="checkbox" name="correct1" ><br>
    <a><fmt:message key="addqus_jsp.answer"/></a><input type="text" name="ans2" value=""> <input type="checkbox" name="correct2" ><br>
    <a><fmt:message key="addqus_jsp.answer"/></a><input type="text" name="ans3" value=""> <input type="checkbox" name="correct3" ><br>
    <a><fmt:message key="addqus_jsp.answer"/></a><input type="text" name="ans4" value=""> <input type="checkbox" name="correct4" ><br>
    <%
        int count = 2;
        if (request.getParameter("addanswer")!=null){
            out.print("<a>введите ответ</a><input type=\"text\" name=\"ans" + count + "\" > <input type=\"checkbox\" name=\"correct" + count + "\"><br>");
            count++;

        }
    %>
    <input type="submit" name="add" value='<fmt:message key="addqus_jsp.add_question"/>'>
    <input type="submit" name="finish" value=<fmt:message key="addqus_jsp.finish"/>>
</form>
<form hidden method="get">
<input type="submit" name="addanswer" value="добавить вариант ответа">
</form>
</body>
</html>
