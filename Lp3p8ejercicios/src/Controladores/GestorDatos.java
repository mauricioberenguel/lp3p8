package Controladores;

import java.util.Date;
import java.util.List;

import Modelos.Carrito;
import Modelos.Cliente;
import Modelos.ItemCarrito;
import Modelos.Orden;
import Modelos.Pedido;
import Modelos.Producto;

public class GestorDatos {
    private final ClienteController clienteController;
    private final ProductoController productoController;
    private final CarritoController carritoController;
    private final ItemCarritoController itemCarritoController;
    private final OrdenController ordenController;
    private final PedidoController pedidoController;
    private final String claveAutorizacion = "1234"; // Clave para autorización de operaciones

    public GestorDatos() {
        this.clienteController = new ClienteController();
        this.productoController = new ProductoController();
        this.carritoController = new CarritoController();
        this.itemCarritoController = new ItemCarritoController();
        this.ordenController = new OrdenController();
        this.pedidoController = new PedidoController();
    }

    private boolean autorizarOperacion(String clave) {
        return clave.equals(claveAutorizacion);
    }
    
 // Métodos CRUD para Cliente
    public void agregarCliente(int id, String nombre, String email, String clave) {
        if (autorizarOperacion(clave)) {
            clienteController.agregarCliente(id, nombre, email);
            System.out.println("Cliente agregado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    public void mostrarClientes() {
        List<Cliente> clientes = clienteController.obtenerClientes();
        clientes.forEach(cliente -> 
            System.out.println(cliente.getId() + " | " + cliente.getNombre() + " | " + cliente.getEmail())
        );
    }

    public void actualizarCliente(int id, String nuevoNombre, String nuevoEmail, String clave) {
        if (autorizarOperacion(clave)) {
            clienteController.actualizarCliente(id, nuevoNombre, nuevoEmail);
            System.out.println("Cliente actualizado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    public void eliminarCliente(int id, String clave) {
        if (autorizarOperacion(clave)) {
            clienteController.eliminarCliente(id);
            System.out.println("Cliente eliminado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    // Métodos CRUD para Producto
    public void agregarProducto(int id, String nombre, double precio, String descripcion, String clave) {
        if (autorizarOperacion(clave)) {
            productoController.agregarProducto(id, nombre, precio, descripcion);
            System.out.println("Producto agregado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    public void mostrarProductos() {
        List<Producto> productos = productoController.obtenerProductos();
        productos.forEach(producto -> 
            System.out.println(producto.getId() + " | " + producto.getNombre() + " | " + producto.getPrecio() + " | " + producto.getDescripcion())
        );
    }

    public void actualizarProducto(int id, String nuevoNombre, double nuevoPrecio, String nuevaDescripcion, String clave) {
        if (autorizarOperacion(clave)) {
            productoController.actualizarProducto(id, nuevoNombre, nuevoPrecio, nuevaDescripcion);
            System.out.println("Producto actualizado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    public void eliminarProducto(int id, String clave) {
        if (autorizarOperacion(clave)) {
            productoController.eliminarProducto(id);
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }
    // Métodos CRUD para Carrito
    public void agregarCarrito(int id, int clienteId, Date fechaCreacion, String clave) {
        if (autorizarOperacion(clave)) {
            carritoController.agregarCarrito(id, clienteId, fechaCreacion);
            System.out.println("Carrito agregado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    public void mostrarCarritos() {
        List<Carrito> carritos = carritoController.obtenerCarritos();
        carritos.forEach(carrito ->
            System.out.println(carrito.getId() + " | " + carrito.getClienteId() + " | " + carrito.getFechaCreacion())
        );
    }

    public void actualizarCarrito(int id, int nuevoClienteId, Date nuevaFechaCreacion, String clave) {
        if (autorizarOperacion(clave)) {
            carritoController.actualizarCarrito(id, nuevoClienteId, nuevaFechaCreacion);
            System.out.println("Carrito actualizado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    public void eliminarCarrito(int id, String clave) {
        if (autorizarOperacion(clave)) {
            carritoController.eliminarCarrito(id);
            System.out.println("Carrito eliminado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }
    
 // Métodos CRUD para ItemCarrito
    public void agregarItemCarrito(int id, int productoId, int carritoId, int cantidad, double subtotal, String clave) {
        if (autorizarOperacion(clave)) {
        	itemCarritoController.agregarItemCarrito(id, productoId, carritoId, cantidad, subtotal);
            System.out.println("Item agregado al carrito.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    public void mostrarItemsCarrito() {
        List<ItemCarrito> itemsCarrito = itemCarritoController.obtenerItemsCarrito();
        itemsCarrito.forEach(item ->
            System.out.println(item.getId() + " | " + item.getProductoId() + " | " + item.getCantidad() + " | " + item.getSubtotal())
        );
    }

    public void actualizarItemCarrito(int id, int nuevaCantidad, double nuevoSubtotal, String clave) {
        if (autorizarOperacion(clave)) {
            itemCarritoController.actualizarItemCarrito(id, nuevaCantidad, nuevoSubtotal);
            System.out.println("Item del carrito actualizado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    public void eliminarItemCarrito(int id, String clave) {
        if (autorizarOperacion(clave)) {
            itemCarritoController.eliminarItemCarrito(id);
            System.out.println("Item del carrito eliminado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    // Métodos CRUD para Orden
    public void agregarOrden(Orden orden, String clave) {
        if (autorizarOperacion(clave)) {
            ordenController.agregarOrden(orden);
            System.out.println("Orden agregada.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    public void mostrarOrdenes() {
        List<Orden> ordenes = ordenController.obtenerOrdenes();
        ordenes.forEach(orden ->
            System.out.println(orden.getId() + " | " + orden.getTotal() + " | " + orden.getFechaOrden())
        );
    }

    public void actualizarOrden(int id, double nuevoTotal, Date nuevaFechaOrden, String clave) {
        if (autorizarOperacion(clave)) {
            ordenController.actualizarOrden(id, nuevoTotal, nuevaFechaOrden);
            System.out.println("Orden actualizada.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    public void eliminarOrden(int id, String clave) {
        if (autorizarOperacion(clave)) {
            ordenController.eliminarOrden(id);
            System.out.println("Orden eliminada.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    // Métodos CRUD para Pedido
    public void agregarPedido(int id, int ordenId, String estado, Date fechaEnvio, String direccionEnvio, String clave) {
        if (autorizarOperacion(clave)) {
            pedidoController.agregarPedido(id, ordenId, estado, fechaEnvio, direccionEnvio);
            System.out.println("Pedido agregado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    public void mostrarPedidos() {
        List<Pedido> pedidos = pedidoController.obtenerPedidos();
        pedidos.forEach(pedido ->
            System.out.println(pedido.getId() + " | " + pedido.getEstado() + " | " + pedido.getFechaEnvio() + " | " + pedido.getDireccionEnvio())
        );
    }

    public void actualizarPedido(int id, String nuevoEstado, Date nuevaFechaEnvio, String nuevaDireccionEnvio, String clave) {
        if (autorizarOperacion(clave)) {
            pedidoController.actualizarPedido(id, nuevoEstado, nuevaFechaEnvio, nuevaDireccionEnvio);
            System.out.println("Pedido actualizado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    public void eliminarPedido(int id, String clave) {
        if (autorizarOperacion(clave)) {
            pedidoController.eliminarPedido(id);
            System.out.println("Pedido eliminado.");
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }
}

