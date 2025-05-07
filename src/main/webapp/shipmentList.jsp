<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="uts.isd.model.Shipment" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Shipment List</title>
</head>
<body>
<h2>All Shipments</h2>

<%
    // Assume 'shipments' is being passed to the JSP from the servlet
    List<Shipment> shipments = (List<Shipment>) request.getAttribute("shipments");
    if (shipments != null && !shipments.isEmpty()) {
%>
<table border="1">
    <thead>
    <tr>
        <th>Shipment ID</th>
        <th>Order ID</th>
        <th>Shipping Method</th>
        <th>Shipment Date</th>
        <th>Shipping Address</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Loop through the shipments and display them
        for (Shipment shipment : shipments) {
    %>
    <tr>
        <td><%= shipment.getShipmentId() %></td>
        <td><%= shipment.getOrderId() %></td>
        <td><%= shipment.getMethod() %></td>
        <td><%= shipment.getShipmentDate() %></td>
        <td><%= shipment.getAddress() %></td>
        <td>
            <!-- Link to view shipment details -->
            <a href="ViewShipmentServlet?shipmentId=<%= shipment.getShipmentId() %>">View</a>
            <!-- Link to edit shipment -->
            <a href="UpdateShipmentServlet?shipmentId=<%= shipment.getShipmentId() %>">Edit</a>
            <!-- Form to delete shipment -->
            <form action="DeleteShipmentServlet" method="post" style="display:inline;">
                <input type="hidden" name="shipmentId" value="<%= shipment.getShipmentId() %>" />
                <button type="submit">Delete</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<%
} else {
%>
<p>No shipments found.</p>
<%
    }
%>

<p><a href="index.jsp">Back to Home</a></p>
</body>
</html>
