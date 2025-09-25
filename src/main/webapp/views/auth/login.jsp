<%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 9/24/25
  Time: 09:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
    String path = request.getQueryString();
    String message = "";
    if (path == null) {
        message = "";
    } else {
        if (path.contains("error")) {
            message = "Invalid username or password!";
        }
    }


%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var = "i" begin = "1" end = "5">
Item <c:out value = "${i}"/><p>

    </c:forEach>

<% if(!message.isEmpty()) { %>
    <p style="color: red;"><%= message %></p>
<% } %>
<form method="post" action="/auth/login">
    <h1>Login Page</h1>
    <label for="username">Username:</label>
    <input type="text" id="username" name="username"><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
