<%--
  Created by IntelliJ IDEA.
  User: laz
  Date: 30/4/2025
  Time: 4:05 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="uts.isd.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%
  User user = (User) session.getAttribute("loggedInUser");
  String userName = (user != null && user.getName() != null) ? user.getName() : "Guest";
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Welcome - IoT Bay</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="content">
  <h1>Welcome to IoT Bay, <%= userName %>!</h1>
  <p>Your premier destination for high-quality IoT devices and technology solutions. Explore our extensive collection of smart devices, components, and accessories that will transform your digital experience.</p>
  <p><a href="main.jsp">Explore More Devices</a></p>
</div>
</body>
</html>

