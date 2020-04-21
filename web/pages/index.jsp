<%@ page import="com.model.Question" %>
<%@ page import="com.dao.DAOConnection" %>
<%@ page import="com.dao.OracleDAOConnection" %><%--
  Created by IntelliJ IDEA.
  User: deus
  Date: 21.04.2020
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Main Page</title>
  </head>
  <body>
  <h1>Hello - <%=request.getParameter("username")%></h1>
  <%
    DAOConnection connection = OracleDAOConnection.getInstance();
    Question question = connection.getRandomQuestion();
  %>
  <h1><%=question.getQuestion()%></h1>
  <form action="/test?username=<%=request.getParameter("username")%>&questionId=<%=question.getId()%>" method="post">
    <input type="text" name="userAnswer" placeholder="Your answer">
    <input type="submit" value="Answer">
  </form>
  </body>
</html>
