package Vistas;

import java.sql.SQLException;

import BD.DatabaseConnection;
import Controladores.GestorDatos;
import Controladores.MenuController;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        GestorDatos gestorDatos = new GestorDatos();
        MenuController menuController = new MenuController(gestorDatos);
        menuController.mostrarMenu();
        dbConnection.closeConnection();
    }
}
