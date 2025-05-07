<%--
  Created by IntelliJ IDEA.
  User: laz
  Date: 30/4/2025
  Time: 4:03 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if (session != null) {
    session.removeAttribute("user");
    session.invalidate();
  }
  response.sendRedirect("login.jsp");
%>

