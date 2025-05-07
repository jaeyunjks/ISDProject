package uts.isd.controller;

import jakarta.servlet.annotation.WebServlet;
import uts.isd.model.dao.ShipmentDBManager;
import uts.isd.model.Shipment;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UpdateShipmentServlet")
public class UpdateShipmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve shipment details from the form request
        int shipmentId = Integer.parseInt(request.getParameter("shipmentId"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String method = request.getParameter("method");
        String shipmentDate = request.getParameter("shipmentDate");
        String address = request.getParameter("address");

        // Create a Shipment object using the data from the request
        Shipment shipment = new Shipment(shipmentId, orderId, method, shipmentDate, address);

        // Initialize dbManager
        ShipmentDBManager dbManager = null;
        try {
            dbManager = new ShipmentDBManager();  // Database connection
        } catch (SQLException e) {
            request.setAttribute("error", "Error establishing database connection: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
            return;  // Terminate the method if DB connection fails
        }

        // Try updating the shipment in the database
        try {
            dbManager.updateShipment(shipment);  // Call the DB method to update shipment

            // If successful, redirect to the shipment list page
            response.sendRedirect("shipmentList.jsp");  // Redirect to a success page

        } catch (SQLException e) {
            // If an error occurs, log the exception and show an error page
            e.printStackTrace();
            request.setAttribute("error", "Error updating shipment: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);  // Forward to the error page
        } finally {
            try {
                if (dbManager != null) {
                    dbManager.close();  // Close the DB connection after use
                }
            } catch (SQLException e) {
                e.printStackTrace();  // Log any errors while closing DB connection
            }
        }
    }
}
