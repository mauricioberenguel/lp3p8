package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:carritocompras.db";
    private Connection connection;

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Conexión a la base de datos establecida.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    // Método para ejecutar consultas de lectura
    public ResultSet executeQuery(String query, Object... parameters) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        setParameters(stmt, parameters);
        return stmt.executeQuery();
    }

    // Método para ejecutar operaciones de actualización (INSERT, UPDATE, DELETE)
    public int executeUpdate(String query, Object... parameters) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        setParameters(stmt, parameters);
        return stmt.executeUpdate();
    }

    private void setParameters(PreparedStatement stmt, Object[] parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            stmt.setObject(i + 1, parameters[i]);
        }
    }
}
