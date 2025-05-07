package uts.isd.model.dao;

import uts.isd.model.Shipment;

import java.sql.*;
import java.util.*;

public class ShipmentDBManager {
    private final Connection conn;
    
    public ShipmentDBManager() throws SQLException {
        // Use proper connection string for your database
        this.conn = DriverManager.getConnection("jdbc:sqlite:yourDatabasePath.db");  // Update with your database path or URL
        // Create the Shipment table if it doesn't already exist
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Shipment (" +
                "shipmentId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "orderId INTEGER, " +
                "method TEXT, " +
                "shipmentDate TEXT, " +
                "address TEXT)");
    }

    // Method to add a new shipment to the database
    public void addShipment(int orderId, String method, String shipmentDate, String address) throws SQLException {
        String sql = "INSERT INTO Shipment(orderId, method, shipmentDate, address) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setString(2, method);
            ps.setString(3, shipmentDate);
            ps.setString(4, address);
            ps.executeUpdate();
        }
    }

    // Method to get a shipment by its ID
    public Shipment getShipment(int shipmentId) throws SQLException {
        String sql = "SELECT * FROM Shipment WHERE shipmentId=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, shipmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Shipment(
                        rs.getInt("shipmentId"),
                        rs.getInt("orderId"),
                        rs.getString("method"),
                        rs.getString("shipmentDate"),
                        rs.getString("address")
                );
            }
            return null; // Return null if no shipment was found
        }
    }

    // Method to get all shipments from the database
    public List<Shipment> getAllShipments() throws SQLException {
        List<Shipment> list = new ArrayList<>();
        String sql = "SELECT * FROM Shipment";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new Shipment(
                        rs.getInt("shipmentId"),
                        rs.getInt("orderId"),
                        rs.getString("method"),
                        rs.getString("shipmentDate"),
                        rs.getString("address")
                ));
            }
        }
        return list;
    }

    // Method to update a shipment in the database
    public void updateShipment(Shipment shipment) throws SQLException {
        String sql = "UPDATE Shipment SET method=?, shipmentDate=?, address=? WHERE shipmentId=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, shipment.getMethod());
            ps.setString(2, shipment.getShipmentDate());
            ps.setString(3, shipment.getAddress());
            ps.setInt(4, shipment.getShipmentId());
            ps.executeUpdate();
        }
    }

    // Method to delete a shipment from the database
    public void deleteShipment(int shipmentId) throws SQLException {
        String sql = "DELETE FROM Shipment WHERE shipmentId=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, shipmentId);
            ps.executeUpdate();
        }
    }

    // Close the database connection
    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
