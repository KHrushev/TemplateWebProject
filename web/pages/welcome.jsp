<%--
  Created by IntelliJ IDEA.
  User: deus
  Date: 21.04.2020
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <form action="/TemplateWebProject_war_exploded/main?action=test" method="post">
        <input type="text" name="username" placeholder="Name">
        <input type="submit" value="Get test">
    </form>
</body>
</html>
