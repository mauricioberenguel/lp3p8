package Modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BD.DatabaseConnection;

public class Carrito {
    private int id;
    private int clienteId;
    private Date fechaCreacion;

    // Constructor para inicializar un objeto Carrito con valores específicos
    public Carrito(int id, int clienteId, Date fechaCreacion) {
        this.id = id;
        this.clienteId = clienteId;
        this.fechaCreacion = fechaCreacion;
    }

    // CRUD - Método para agregar un carrito a la base de datos
    public static void agregarCarrito(int id, int clienteId, Date fechaCreacion) {
        // Se crea una instancia de DatabaseConnection para establecer conexión a la base de datos
        DatabaseConnection db = new DatabaseConnection();
        String query = "INSERT INTO Carrito (id, cliente_id, fecha_creacion) VALUES (?, ?, ?)";
        
        // Intenta insertar un nuevo registro en la tabla Carrito usando una declaración preparada
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id); // Establece el valor del ID
            stmt.setInt(2, clienteId); // Establece el ID del cliente
            stmt.setDate(3, new java.sql.Date(fechaCreacion.getTime())); // Convierte la fecha de creación a java.sql.Date
            stmt.executeUpdate(); // Ejecuta la inserción en la base de datos
        } catch (SQLException e) {
            System.out.println("Error al agregar el carrito: " + e.getMessage()); // Manejo de error si ocurre un problema en la inserción
        }
    }

    // Métodos getters y setters para acceder y modificar los atributos de la clase
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // CRUD - Método para obtener todos los registros de carritos de la base de datos
    public static List<Carrito> obtenerCarritos() {
        List<Carrito> carritos = new ArrayList<>(); // Lista para almacenar los carritos obtenidos
        DatabaseConnection db = new DatabaseConnection();
        String query = "SELECT * FROM carrito";
        
        // Ejecuta una consulta para obtener todos los registros en la tabla Carrito
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            // Recorre el ResultSet y crea un nuevo objeto Carrito para cada registro encontrado
            while (rs.next()) {
                carritos.add(new Carrito(rs.getInt("id"), rs.getInt("cliente_id"), rs.getDate("fecha_creacion")));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener carritos: " + e.getMessage()); // Manejo de error si ocurre un problema en la consulta
        }
        return carritos; // Devuelve la lista de carritos
    }

    // CRUD - Método para actualizar un carrito en la base de datos
    public static void actualizarCarrito(int id, int nuevoClienteId, Date nuevaFechaCreacion) {
        DatabaseConnection db = new DatabaseConnection();
        String query = "UPDATE Carrito SET cliente_id = ?, fecha_creacion = ? WHERE id = ?";
        
        // Intenta actualizar un registro de carrito usando una declaración preparada
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nuevoClienteId); // Establece el nuevo ID del cliente
            stmt.setDate(2, new java.sql.Date(nuevaFechaCreacion.getTime())); // Convierte y establece la nueva fecha de creación
            stmt.setInt(3, id); // Establece el ID del carrito a actualizar
            stmt.executeUpdate(); // Ejecuta la actualización en la base de datos
        } catch (SQLException e) {
            System.out.println("Error al actualizar el carrito: " + e.getMessage()); // Manejo de error si ocurre un problema en la actualización
        }
    }

    // CRUD - Método para eliminar un carrito de la base de datos
    public static void eliminarCarrito(int id) {
        DatabaseConnection db = new DatabaseConnection();
        String query = "DELETE FROM Carrito WHERE id = ?";
        
        // Intenta eliminar un registro de carrito usando una declaración preparada
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id); // Establece el ID del carrito a eliminar
            stmt.executeUpdate(); // Ejecuta la eliminación en la base de datos
        } catch (SQLException e) {
            System.out.println("Error al eliminar el carrito: " + e.getMessage()); // Manejo de error si ocurre un problema en la eliminación
        }
    }
}