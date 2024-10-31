package Modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import BD.DatabaseConnection;

public class Orden {
    private int id;
    private int carritoId;
    private double total;
    private Date fechaOrden;

    // Constructor para inicializar un objeto Orden con valores específicos
    public Orden(int id, int carritoId, double total, Date fechaOrden) {
        this.id = id;
        this.carritoId = carritoId;
        this.total = total;
        this.fechaOrden = fechaOrden;
    }

    // Getters y setters para acceder y modificar los atributos de la clase
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCarritoId() { return carritoId; }
    public void setCarritoId(int carritoId) { this.carritoId = carritoId; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public Date getFechaOrden() { return fechaOrden; }
    public void setFechaOrden(Date fechaOrden) { this.fechaOrden = fechaOrden; }

    // CRUD - Agregar Orden
    // Método para agregar una nueva orden a la base de datos
    public static void agregarOrden(int id, int carritoId, double total, Date fechaOrden) {
        DatabaseConnection db = new DatabaseConnection();
        String query = "INSERT INTO Orden (id, carrito_id, total, fecha_orden) VALUES (?, ?, ?, ?)";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Establece los valores de la consulta con los datos recibidos
            stmt.setInt(1, id);
            stmt.setInt(2, carritoId);
            stmt.setDouble(3, total);
            stmt.setDate(4, new java.sql.Date(fechaOrden.getTime())); // Convertir a java.sql.Date
            stmt.executeUpdate(); // Ejecuta la consulta de inserción
        } catch (SQLException e) {
            System.out.println("Error al agregar la orden: " + e.getMessage()); // Manejo de errores
        }
    }

    // CRUD - Obtener todas las órdenes
    // Método para obtener todas las órdenes almacenadas en la base de datos
    public static List<Orden> obtenerOrdenes() {
        List<Orden> ordenes = new ArrayList<>(); // Lista para almacenar las órdenes obtenidas
        DatabaseConnection db = new DatabaseConnection();
        String query = "SELECT * FROM Orden";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            // Recorre el ResultSet y crea un objeto Orden por cada registro
            while (rs.next()) {
                ordenes.add(new Orden(
                        rs.getInt("id"),
                        rs.getInt("carrito_id"),
                        rs.getDouble("total"),
                        rs.getDate("fecha_orden")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las órdenes: " + e.getMessage()); // Manejo de errores
        }
        return ordenes; // Devuelve la lista de órdenes obtenidas
    }

    // CRUD - Actualizar Orden
    // Método para actualizar el total y la fecha de una orden en la base de datos
    public static void actualizarOrden(int id, double nuevoTotal, Date nuevaFechaOrden) {
        DatabaseConnection db = new DatabaseConnection();
        String query = "UPDATE Orden SET total = ?, fecha_orden = ? WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Establece los nuevos valores para total y fecha
            stmt.setDouble(1, nuevoTotal);
            stmt.setDate(2, new java.sql.Date(nuevaFechaOrden.getTime())); // Convertir a java.sql.Date
            stmt.setInt(3, id);
            stmt.executeUpdate(); // Ejecuta la consulta de actualización
        } catch (SQLException e) {
            System.out.println("Error al actualizar la orden: " + e.getMessage()); // Manejo de errores
        }
    }

    // CRUD - Eliminar Orden
    // Método para eliminar una orden en la base de datos
    public static void eliminarOrden(int id) {
        DatabaseConnection db = new DatabaseConnection();
        String query = "DELETE FROM Orden WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id); // Establece el ID de la orden a eliminar
            stmt.executeUpdate(); // Ejecuta la consulta de eliminación
        } catch (SQLException e) {
            System.out.println("Error al eliminar la orden: " + e.getMessage()); // Manejo de errores
        }
    }
}