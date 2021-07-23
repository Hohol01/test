<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title>ADDING TEST</title>

</head>
<body>
<form method="post">
    <%@include file="/WEB-INF/jspf/language.jspf" %>
</form>
<form method="post">
    <a><fmt:message key="addqus_jsp.question"/> </a><input type="text" name="question"><br>
    <a><fmt:message key="addqus_jsp.answer"/> </a><input type="text" name="ans1"> <input type="checkbox"
                                                                                         name="correct1"><br>
    <a><fmt:message key="addqus_jsp.answer"/></a><input type="text" name="ans2"> <input type="checkbox" name="correct2"><br>
    <a><fmt:message key="addqus_jsp.answer"/></a><input type="text" name="ans3"> <input type="checkbox" name="correct3"><br>
    <a><fmt:message key="addqus_jsp.answer"/></a><input type="text" name="ans4" value=""> <input type="checkbox"
                                                                                                 name="correct4"><br>
    <%
        int count = 2;
        if (request.getParameter("addanswer") != null) {
            out.print("<a>введите ответ</a><input type=\"text\" name=\"ans" + count + "\" > <input type=\"checkbox\" name=\"correct" + count + "\"><br>");
            count++;

        }
    %>
    <c:if test="${requestScope.error!=null}">
       <fmt:message key="addqus_jsp.error"/><br>
    </c:if>
    <input type="submit" name="add" value='<fmt:message key="addqus_jsp.add_question"/>'>
    <input type="submit" name="finish" value='<fmt:message key="addqus_jsp.finish"/>'>
</form>
<form hidden method="get">
    <input type="submit" name="addanswer" value="добавить вариант ответа">
</form>
</body>
</html>
