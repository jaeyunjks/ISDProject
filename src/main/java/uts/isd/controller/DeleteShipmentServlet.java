package uts.isd.controller;

import uts.isd.model.dao.ShipmentDBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteShipmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the shipmentId from the request parameter
        int shipmentId = Integer.parseInt(request.getParameter("shipmentId"));

        // Initialize the ShipmentDBManager object and attempt to delete the shipment
        ShipmentDBManager dbManager = null;
        try {
            dbManager = new ShipmentDBManager();
            dbManager.deleteShipment(shipmentId);  // Call the DB method to delete shipment
        } catch (SQLException e) {
            // If there's an exception (e.g., database error), log it and forward the error message
            e.printStackTrace();
            request.setAttribute("error", "Error deleting shipment: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);  // Forward to the error page
            return;  // Ensure no further execution happens after forwarding
        }

        // If deletion is successful, redirect to the shipment list page
        response.sendRedirect("shipmentList.jsp");
    }
}
