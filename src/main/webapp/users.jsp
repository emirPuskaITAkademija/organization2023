<%@ page import="ba.celebration.organization.user.ejb.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Grupa 1
  Date: 5/25/2023
  Time: 8:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% List<User> userList = (List<User>) request.getAttribute("korisnici"); %>
<ol>
    <%
        for (User user : userList) {
    %>
    <li><div><%=user.getName()%>  <%=user.getSurname()%></div>
    </li>
    <%}%>
</ol>
</body>
</html>
