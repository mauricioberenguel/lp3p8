package Controladores;
import java.util.Date;
import java.util.List;
import Modelos.Pedido;

public class PedidoController {
    private List<Pedido> pedidos;

    public PedidoController() {
        cargarPedidosDesdeBD();
    }

    // Método para cargar todos los pedidos de la base de datos a la lista en memoria
    private void cargarPedidosDesdeBD() {
        pedidos = obtenerPedidos(); //Obtiene los pedidos
    }

    // Agrega un pedido a la base de datos y a la lista en memoria
    public void agregarPedido(int id, int ordenId, String estado, Date fechaEnvio, String direccionEnvio) {
        Pedido.agregarPedido(id, ordenId, estado, fechaEnvio, direccionEnvio);
        pedidos.add(new Pedido(id, ordenId, estado, fechaEnvio, direccionEnvio));
    }

    // Elimina un pedido de la base de datos y de la lista en memoria
    public void eliminarPedido(int id) {
        Pedido.eliminarPedido(id);
        pedidos.removeIf(pedido -> pedido.getId() == id);
    }

    // Actualiza un pedido en la base de datos y en la lista en memoria
    public void actualizarPedido(int id, String nuevoEstado, Date nuevaFechaEnvio, String nuevaDireccionEnvio) {
        Pedido.actualizarPedido(id, nuevoEstado, nuevaFechaEnvio, nuevaDireccionEnvio);
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                pedido.setEstado(nuevoEstado);
                pedido.setFechaEnvio(nuevaFechaEnvio);
                pedido.setDireccionEnvio(nuevaDireccionEnvio);
            }
        }
    }

    // Devuelve la lista de pedidos cargada en memoria
    public List<Pedido> obtenerPedidos() {
        return pedidos;
    }

    // Obtiene un pedido por ID desde la lista en memoria
    public Pedido obtenerPedidoPorId(int id) {
        return pedidos.stream().filter(pedido -> pedido.getId() == id).findFirst().orElse(null);
    }
}