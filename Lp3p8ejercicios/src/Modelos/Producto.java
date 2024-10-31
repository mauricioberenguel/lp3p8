package Modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import BD.DatabaseConnection;

public class Producto {
    // Atributos de la clase Producto
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;

    // Constructor para inicializar un objeto Producto con valores específicos
    public Producto(int id, String nombre, double precio, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    // Método CRUD para agregar un nuevo producto a la base de datos
    public static void agregarProducto(int id, String nombre, double precio, String descripcion) {
        DatabaseConnection db = new DatabaseConnection();
        String query = "INSERT INTO Producto (id, nombre, precio, descripcion) VALUES (?, ?, ?, ?)";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Asigna los valores a los parámetros de la consulta
            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.setDouble(3, precio);
            stmt.setString(4, descripcion);
            stmt.executeUpdate(); // Ejecuta la consulta de inserción
        } catch (SQLException e) {
            System.out.println("Error al agregar el producto: " + e.getMessage()); // Manejo de errores
        }
    }

    // Método CRUD para obtener todos los productos de la base de datos
    public static List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>(); // Lista para almacenar productos
        DatabaseConnection db = new DatabaseConnection();
        String query = "SELECT * FROM Producto";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            // Itera sobre el resultado y crea un objeto Producto por cada fila
            while (rs.next()) {
                productos.add(new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion")));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener productos: " + e.getMessage()); // Manejo de errores
        }
        return productos; // Retorna la lista de productos
    }

    // Método CRUD para actualizar un producto existente en la base de datos
    public static void actualizarProducto(int id, String nuevoNombre, double nuevoPrecio, String nuevaDescripcion) {
        DatabaseConnection db = new DatabaseConnection();
        String query = "UPDATE Producto SET nombre = ?, precio = ?, descripcion = ? WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Asigna los valores nuevos a los parámetros de la consulta
            stmt.setString(1, nuevoNombre);
            stmt.setDouble(2, nuevoPrecio);
            stmt.setString(3, nuevaDescripcion);
            stmt.setInt(4, id);
            stmt.executeUpdate(); // Ejecuta la consulta de actualización
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage()); // Manejo de errores
        }
    }

    // Método CRUD para eliminar un producto de la base de datos por su ID
    public static void eliminarProducto(int id) {
        DatabaseConnection db = new DatabaseConnection();
        String query = "DELETE FROM Producto WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Asigna el valor del ID al parámetro de la consulta
            stmt.setInt(1, id);
            stmt.executeUpdate(); // Ejecuta la consulta de eliminación
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage()); // Manejo de errores
        }
    }

    // Getters y setters para acceder y modificar los atributos de la clase
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}