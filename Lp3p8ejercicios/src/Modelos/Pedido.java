package Modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import BD.DatabaseConnection;

public class Pedido {
    // Atributos de la clase Pedido
    private int id;
    private int ordenId;
    private String estado;
    private Date fechaEnvio;
    private String direccionEnvio;
    
    // Conexión a la base de datos
    private static DatabaseConnection dbConnection = new DatabaseConnection();

    // Constructor para inicializar un objeto Pedido con valores específicos
    public Pedido(int id, int ordenId, String estado, Date fechaEnvio, String direccionEnvio) {
        this.id = id;
        this.ordenId = ordenId;
        this.estado = estado;
        this.fechaEnvio = fechaEnvio;
        this.direccionEnvio = direccionEnvio;
    }

    // Métodos CRUD para realizar operaciones de base de datos

    // Método para agregar un nuevo pedido a la base de datos
    public static void agregarPedido(int id, int ordenId, String estado, Date fechaEnvio, String direccionEnvio) {
        String query = "INSERT INTO Pedido (id, orden_id, estado, fecha_envio, direccion_envio) VALUES (?, ?, ?, ?, ?)";
        try {
            // Ejecuta la consulta de inserción con los parámetros dados
            dbConnection.executeUpdate(query, id, ordenId, estado, fechaEnvio, direccionEnvio);
            System.out.println("Pedido agregado.");
        } catch (SQLException e) {
            System.out.println("Error al agregar pedido: " + e.getMessage()); // Manejo de errores
        }
    }

    // Método para obtener un pedido de la base de datos por su ID
    public static Pedido obtenerPedidoPorId(int id) {
        String query = "SELECT * FROM Pedido WHERE id = ?";
        try (ResultSet rs = dbConnection.executeQuery(query, id)) {
            if (rs.next()) {
                // Retorna un nuevo objeto Pedido con los datos obtenidos de la base de datos
                return new Pedido(
                    rs.getInt("id"),
                    rs.getInt("orden_id"),
                    rs.getString("estado"),
                    rs.getDate("fecha_envio"),
                    rs.getString("direccion_envio")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pedido: " + e.getMessage()); // Manejo de errores
        }
        return null; // Retorna null si no se encuentra el pedido
    }

    // Método para actualizar el estado, la fecha de envío y la dirección de un pedido
    public static void actualizarPedido(int id, String nuevoEstado, Date nuevaFechaEnvio, String nuevaDireccionEnvio) {
        String query = "UPDATE Pedido SET estado = ?, fecha_envio = ?, direccion_envio = ? WHERE id = ?";
        try {
            // Ejecuta la consulta de actualización con los nuevos valores
            dbConnection.executeUpdate(query, nuevoEstado, nuevaFechaEnvio, nuevaDireccionEnvio, id);
            System.out.println("Pedido actualizado.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar pedido: " + e.getMessage()); // Manejo de errores
        }
    }

    // Método para eliminar un pedido de la base de datos por su ID
    public static void eliminarPedido(int id) {
        String query = "DELETE FROM Pedido WHERE id = ?";
        try {
            // Ejecuta la consulta de eliminación
            dbConnection.executeUpdate(query, id);
            System.out.println("Pedido eliminado.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar pedido: " + e.getMessage()); // Manejo de errores
        }
    }

    // Getters y setters para acceder y modificar los atributos de la clase
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getOrdenId() { return ordenId; }
    public void setOrdenId(int ordenId) { this.ordenId = ordenId; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Date getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(Date fechaEnvio) { this.fechaEnvio = fechaEnvio; }
    public String getDireccionEnvio() { return direccionEnvio; }
    public void setDireccionEnvio(String direccionEnvio) { this.direccionEnvio = direccionEnvio; }
}