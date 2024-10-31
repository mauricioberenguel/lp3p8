package Modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import BD.DatabaseConnection;

public class ItemCarrito {
    private int id;
    private int carritoId;
    private int productoId;
    private int cantidad;
    private double subtotal;

    // Constructor para inicializar un objeto ItemCarrito con valores específicos
    public ItemCarrito(int id, int carritoId, int productoId, int cantidad, double subtotal) {
        this.id = id;
        this.carritoId = carritoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    // CRUD - Agregar ItemCarrito
    // Método para agregar un nuevo item al carrito en la base de datos
    public static void agregarItemCarrito(int id, int carritoId, int productoId, int cantidad, double subtotal) {
        DatabaseConnection db = new DatabaseConnection();
        String query = "INSERT INTO ItemCarrito (id, carrito_id, producto_id, cantidad, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Establece los parámetros de la consulta con los valores recibidos
            stmt.setInt(1, id);
            stmt.setInt(2, carritoId);
            stmt.setInt(3, productoId);
            stmt.setInt(4, cantidad);
            stmt.setDouble(5, subtotal);
            stmt.executeUpdate(); // Ejecuta la consulta de inserción
        } catch (SQLException e) {
            System.out.println("Error al agregar el item al carrito: " + e.getMessage()); // Manejo de errores
        }
    }

    // CRUD - Obtener todos los items de los carritos
    // Método para obtener todos los items del carrito almacenados en la base de datos
    public static List<ItemCarrito> obtenerTodosLosItemsCarrito() {
        List<ItemCarrito> itemsCarrito = new ArrayList<>(); // Lista para almacenar los items obtenidos
        DatabaseConnection db = new DatabaseConnection();
        String query = "SELECT * FROM ItemCarrito";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                // Recorre el ResultSet y crea un objeto ItemCarrito por cada registro
                while (rs.next()) {
                    itemsCarrito.add(new ItemCarrito(
                            rs.getInt("id"),
                            rs.getInt("carrito_id"),
                            rs.getInt("producto_id"),
                            rs.getInt("cantidad"),
                            rs.getDouble("subtotal")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los items de los carritos: " + e.getMessage()); // Manejo de errores
        }
        return itemsCarrito; // Devuelve la lista de items obtenidos
    }

    // CRUD - Actualizar ItemCarrito
    // Método para actualizar la cantidad y el subtotal de un item del carrito en la base de datos
    public static void actualizarItemCarrito(int id, int nuevaCantidad, double nuevoSubtotal) {
        DatabaseConnection db = new DatabaseConnection();
        String query = "UPDATE ItemCarrito SET cantidad = ?, subtotal = ? WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Establece los nuevos valores para cantidad y subtotal
            stmt.setInt(1, nuevaCantidad);
            stmt.setDouble(2, nuevoSubtotal);
            stmt.setInt(3, id);
            stmt.executeUpdate(); // Ejecuta la consulta de actualización
        } catch (SQLException e) {
            System.out.println("Error al actualizar el item del carrito: " + e.getMessage()); // Manejo de errores
        }
    }

    // CRUD - Eliminar ItemCarrito
    // Método para eliminar un item del carrito en la base de datos
    public static void eliminarItemCarrito(int id) {
        DatabaseConnection db = new DatabaseConnection();
        String query = "DELETE FROM ItemCarrito WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id); // Establece el ID del item a eliminar
            stmt.executeUpdate(); // Ejecuta la consulta de eliminación
        } catch (SQLException e) {
            System.out.println("Error al eliminar el item del carrito: " + e.getMessage()); // Manejo de errores
        }
    }

    // Getters y setters para acceder y modificar los atributos de la clase
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCarritoId() { return carritoId; }
    public void setCarritoId(int carritoId) { this.carritoId = carritoId; }
    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) { this.productoId = productoId; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}
