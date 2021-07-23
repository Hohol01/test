<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 22.07.2021
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Error page</title>
</head>
<body>
<button onclick="history.back()">Back to Previous Page</button>
<h1>404 Page Not Found.</h1>
<br/>
<p><b>Error code:</b> ${pageContext.errorData.statusCode}</p>
<p><b>Request URI:</b> ${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}</p>
<br/>
</body>
</html>
