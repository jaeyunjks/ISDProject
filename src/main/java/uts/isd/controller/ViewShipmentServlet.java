package uts.isd.controller;

import uts.isd.model.dao.ShipmentDBManager;
import uts.isd.model.Shipment;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class ViewShipmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the shipmentId from the request parameter
        int shipmentId = Integer.parseInt(request.getParameter("shipmentId"));

        // Initialize the ShipmentDBManager object and attempt to fetch the shipment details
        ShipmentDBManager dbManager = null;
        Shipment shipment = null;
        try {
            dbManager = new ShipmentDBManager();
            shipment = dbManager.getShipment(shipmentId);  // Fetch the shipment details
        } catch (SQLException e) {
            // If there's an exception (e.g., database error), log it and forward the error message
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving shipment: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);  // Forward to the error page
            return;  // Ensure no further execution happens after forwarding
        }

        // If shipment is found, forward it to the JSP page
        if (shipment != null) {
            request.setAttribute("shipment", shipment);
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewShipment.jsp");
            dispatcher.forward(request, response);  // Forward to the view shipment page
        } else {
            // If shipment is not found, show an error message
            request.setAttribute("error", "Shipment not found");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);  // Forward to the error page
        }
    }
}
