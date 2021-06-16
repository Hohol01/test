<%--
  Created by IntelliJ IDEA.
  User: artemmaniakin
  Date: 09.06.2021
  Time: 02:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Тест
    </title>
    <script type="text/javascript">
        var counter = 10
        function time(){
            counter --;
            document.getElementById("count").innerText = counter;
            setTimeout("time()" ,1000);
                if (counter==0){
                    document.getElementById("finish").onsubmit;
                }
        }


    </script>
</head>
<body   onload="time( )">
    секунд прошло<a id="count">-1</a>
</body>
</html>