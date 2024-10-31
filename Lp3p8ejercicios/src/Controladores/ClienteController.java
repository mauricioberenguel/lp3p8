package Controladores;

import java.util.List;
import Modelos.Cliente;

public class ClienteController {
    private List<Cliente> clientes;

    // Constructor que carga los datos de la base de datos
    public ClienteController() {
        cargarClientesDesdeBD();
    }

    // Método para cargar todos los clientes de la base de datos a la lista en memoria
    private void cargarClientesDesdeBD() {
        clientes = Cliente.obtenerClientes();
    }

    // Agrega un cliente tanto a la base de datos como a la lista
    public void agregarCliente(int id, String nombre, String email) {
        Cliente nuevoCliente = new Cliente(id, nombre, email);
        Cliente.agregarCliente(id, nombre, email); // Agrega a la base de datos
        clientes.add(nuevoCliente); // Agrega a la lista local
    }

    // Elimina un cliente de la base de datos y de la lista en memoria
    public void eliminarCliente(int id) {
        Cliente.eliminarCliente(id); // Elimina de la base de datos
        clientes.removeIf(cliente -> cliente.getId() == id); // Elimina de la lista local
    }

    // Actualiza un cliente en la base de datos y en la lista en memoria
    public void actualizarCliente(int id, String nuevoNombre, String nuevoEmail) {
        Cliente.actualizarCliente(id, nuevoNombre, nuevoEmail); // Actualiza en la base de datos
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                cliente.setNombre(nuevoNombre);
                cliente.setEmail(nuevoEmail);
            }
        }
    }

    // Devuelve la lista de clientes cargada en memoria
    public List<Cliente> obtenerClientes() {
        return clientes;
    }
}