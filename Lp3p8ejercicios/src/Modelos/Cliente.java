package Modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import BD.DatabaseConnection;

public class Cliente {
    private int id;
    private String nombre;
    private String email;
    private static DatabaseConnection dbConnection = new DatabaseConnection(); // Conexión estática a la base de datos para operaciones compartidas

    // Constructor para inicializar un objeto Cliente con valores específicos
    public Cliente(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    // Métodos CRUD

    // Método para agregar un nuevo cliente a la base de datos
    public static void agregarCliente(int id, String nombre, String email) {
        String query = "INSERT INTO Cliente (id, nombre, email) VALUES (?, ?, ?)";
        try {
            // Ejecuta la consulta de inserción usando parámetros proporcionados
            dbConnection.executeUpdate(query, id, nombre, email);
            System.out.println("Cliente agregado."); // Confirmación en consola
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage()); // Manejo de errores
        }
    }

    // Método para obtener todos los clientes de la base de datos
    public static List<Cliente> obtenerClientes() {
        String query = "SELECT * FROM Cliente";
        List<Cliente> clientes = new ArrayList<>(); // Lista para almacenar clientes obtenidos
        
        try (ResultSet rs = dbConnection.executeQuery(query)) {
            // Recorre el ResultSet y crea un objeto Cliente para cada registro
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"));
                clientes.add(cliente); // Agrega el cliente a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener clientes: " + e.getMessage()); // Manejo de errores
        }
        return clientes; // Devuelve la lista de clientes obtenidos
    }

    // Método para actualizar los datos de un cliente en la base de datos
    public static void actualizarCliente(int id, String nuevoNombre, String nuevoEmail) {
        String query = "UPDATE Cliente SET nombre = ?, email = ? WHERE id = ?";
        try {
            // Ejecuta la consulta de actualización con los nuevos valores
            dbConnection.executeUpdate(query, nuevoNombre, nuevoEmail, id);
            System.out.println("Cliente actualizado."); // Confirmación en consola
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage()); // Manejo de errores
        }
    }

    // Método para eliminar un cliente de la base de datos
    public static void eliminarCliente(int id) {
        String query = "DELETE FROM Cliente WHERE id = ?";
        try {
            // Ejecuta la consulta de eliminación para el cliente con el ID especificado
            dbConnection.executeUpdate(query, id);
            System.out.println("Cliente eliminado."); // Confirmación en consola
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage()); // Manejo de errores
        }
    }

    // Getters y setters para acceder y modificar los atributos de la clase
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
