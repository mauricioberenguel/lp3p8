package Controladores;

import java.util.Date;
import java.util.List;
import Modelos.Carrito;

public class CarritoController {
    private List<Carrito> carritos;

    public CarritoController() {
        cargarCarritosDesdeBD();
    }

    private void cargarCarritosDesdeBD() {
        carritos = Carrito.obtenerCarritos(); // Cargar carritos desde la base de datos
    }

    public void agregarCarrito(int id, int clienteId, Date fechaCreacion) {
        Carrito.agregarCarrito(id, clienteId, fechaCreacion); // Agrega en la base de datos
        carritos.add(new Carrito(id, clienteId, fechaCreacion)); // Agrega a la lista en memoria
    }

    public void eliminarCarrito(int id) {
        Carrito.eliminarCarrito(id); // Elimina en la base de datos
        carritos.removeIf(carrito -> carrito.getId() == id); // Elimina de la lista en memoria
    }

    public void actualizarCarrito(int id, int nuevoClienteId, Date nuevaFechaCreacion) {
        Carrito.actualizarCarrito(id, nuevoClienteId, nuevaFechaCreacion); // Actualiza en la base de datos
        for (Carrito carrito : carritos) {
            if (carrito.getId() == id) {
                carrito.setClienteId(nuevoClienteId);
                carrito.setFechaCreacion(nuevaFechaCreacion);
            }
        }
    }

    public List<Carrito> obtenerCarritos() {
        return carritos;
    }

    public Carrito obtenerCarritoPorId(int id) {
        for (Carrito carrito : carritos) {
            if (carrito.getId() == id) {
                return carrito;
            }
        }
        return null; // Retorna null si no se encuentra el carrito con el ID especificado
    }
}
