package lp3p8actividades;

import java.sql.*;
import java.util.Scanner;

public class Principal {
    
    // Define la URL de conexión a la base de datos SQLite
    private static final String URL = "jdbc:sqlite:hotel.db";
    
    public static void main(String[] args) {
        // Maneja las excepciones que puedan ocurrir durante la conexión y las operaciones de la base de datos
        try (Connection con = DriverManager.getConnection(URL)) {
            // Carga el driver SQLite JDBC
            Class.forName("org.sqlite.JDBC");
            // Deshabilita el autocommit para realizar transacciones
            con.setAutoCommit(false);
            // Crea la tabla Cliente si no existe
            crearTablaCliente(con);
            
            // Crea un objeto Scanner para leer la entrada del usuario
            Scanner scanner = new Scanner(System.in);
            // Variable para controlar el bucle principal
            boolean continuar = true;
            
            // Bucle principal que muestra el menú de opciones al usuario
            while (continuar) {
                System.out.println("\nSeleccione una operación:");
                System.out.println("1. Insertar cliente");
                System.out.println("2. Ver clientes");
                System.out.println("3. Actualizar cliente");
                System.out.println("4. Eliminar cliente");
                System.out.println("5. Salir");
                
                // Lee la opción seleccionada por el usuario
                int opcion = scanner.nextInt();
                // Consume el salto de línea
                scanner.nextLine(); 
                
                // Ejecuta la acción correspondiente a la opción seleccionada
                switch (opcion) {
                    case 1 -> insertarCliente(con, scanner);
                    case 2 -> recuperarClientes(con);
                    case 3 -> actualizarCliente(con, scanner);
                    case 4 -> eliminarCliente(con, scanner);
                    case 5 -> continuar = false;
                    default -> System.out.println("Opción no válida.");
                }
            }
            
            // Confirma la transacción
            con.commit();
            System.out.println("Transacción completada exitosamente.");
            
        } catch (Exception e) {
            // Imprime un mensaje de error si ocurre una excepción
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Crea la tabla Cliente si no existe
    private static void crearTablaCliente(Connection con) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Cliente (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    email TEXT NOT NULL,
                    telefono TEXT
                );
                """;
        // Crea un objeto PreparedStatement para ejecutar la sentencia SQL
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            // Ejecuta la sentencia SQL
            stmt.execute();
            System.out.println("Tabla Cliente creada exitosamente.");
        }
    }
    
    // Inserta un nuevo cliente en la base de datos
    private static void insertarCliente(Connection con, Scanner scanner) throws SQLException {
        // Obtiene los datos del cliente del usuario
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el email del cliente: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese el teléfono del cliente: ");
        String telefono = scanner.nextLine();
        
        // Crea un objeto PreparedStatement para insertar el cliente
        String sql = "INSERT INTO Cliente (nombre, email, telefono) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            // Establece los valores de los parámetros en la sentencia SQL
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, telefono);
            // Ejecuta la sentencia SQL y obtiene el número de filas insertadas
            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " cliente(s) insertado(s).");
        }
    }

    // Recupera y muestra todos los clientes de la base de datos
    private static void recuperarClientes(Connection con) throws SQLException {
        // Crea un objeto PreparedStatement para seleccionar todos los clientes
        String sql = "SELECT * FROM Cliente";
        try (PreparedStatement stmt = con.prepareStatement(sql);
             // Ejecuta la sentencia SQL y obtiene un objeto ResultSet
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("\nClientes registrados:");
            // Itera sobre los resultados y muestra cada cliente
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                System.out.println(id + " | " + nombre + " | " + email + " | " + telefono);
            }
        }
    }

    // Actualiza los datos de un cliente existente
    private static void actualizarCliente(Connection con, Scanner scanner) throws SQLException {
        // Obtiene el ID del cliente a actualizar
        System.out.print("Ingrese el ID del cliente a actualizar: ");
        int id = scanner.nextInt();
        // Consume el salto de línea
        scanner.nextLine(); 
        
        // Obtiene los nuevos datos del cliente
        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese el nuevo teléfono: ");
        String telefono = scanner.nextLine();
        
        // Crea un objeto PreparedStatement para actualizar el cliente
        String sql = "UPDATE Cliente SET nombre = ?, email = ?, telefono = ? WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            // Establece los valores de los parámetros en la sentencia SQL
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, telefono);
            stmt.setInt(4, id);
            // Ejecuta la sentencia SQL y obtiene el número de filas actualizadas
            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated + " cliente(s) actualizado(s).");
        }
    }

    // Elimina un cliente de la base de datos
    private static void eliminarCliente(Connection con, Scanner scanner) throws SQLException {
        // Obtiene el ID del cliente a eliminar
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        int id = scanner.nextInt();
        
        // Crea un objeto PreparedStatement para eliminar el cliente
        String sql = "DELETE FROM Cliente WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            // Establece el valor del parámetro en la sentencia SQL
            stmt.setInt(1, id);
            // Ejecuta la sentencia SQL y obtiene el número de filas eliminadas
            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted + " cliente(s) eliminado(s).");
        }
    }
}

