<%--
  Created by IntelliJ IDEA.
  User: laz
  Date: 30/4/2025
  Time: 4:03 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="uts.isd.model.User" %>
<%@ page import="uts.isd.model.dao.UserDBManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page session="true" %>
<%
  String message = null;
  if (request.getMethod().equalsIgnoreCase("POST")) {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    if (email != null && password != null && !email.isEmpty() && !password.isEmpty()) {
      Connection connection = (Connection) application.getAttribute("dbConnection"); // Assumed connection is stored in application scope
      UserDBManager userDBManager = new UserDBManager(connection);

      User user = null;
      for (int id = 1; id < Integer.MAX_VALUE; id++) { // Looping through all user IDs
        user = userDBManager.get(id);
        if (user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
          break;
        }
      }

      if (user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
        session.setAttribute("user", user);
        response.sendRedirect("welcome.jsp");
        return;
      } else {
        message = "Invalid email or password!";
      }
    } else {
      message = "Email and password are required!";
    }
  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login - IoT Bay</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="login-form">
  <h2>Login</h2>
  <% if (message != null) { %>
  <p style="color: red;"><%= message %></p>
  <% } %>
  <form method="post">
    <label for="email">Email:</label>
    <input id="email" type="email" name="email" required><br><br>
    <label for="password">Password:</label>
    <input id="password" type="password" name="password" required><br><br>
    <input type="submit" value="Login">
  </form>
</div>
</body>
</html>




