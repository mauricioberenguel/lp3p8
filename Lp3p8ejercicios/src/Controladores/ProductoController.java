package Controladores;

import java.util.List;
import Modelos.Producto;

public class ProductoController {
    private List<Producto> productos;

    public ProductoController() {
        cargarProductosDesdeBD();
    }

    private void cargarProductosDesdeBD() {
        productos = Producto.obtenerProductos(); // Carga productos desde la base de datos
    }

    public void agregarProducto(int id, String nombre, double precio, String descripcion) {
        Producto.agregarProducto(id, nombre, precio, descripcion); // Agrega a la base de datos
        productos.add(new Producto(id, nombre, precio, descripcion)); // Agrega a la lista local
    }

    public void eliminarProducto(int id) {
        Producto.eliminarProducto(id); // Elimina de la base de datos
        productos.removeIf(producto -> producto.getId() == id); // Elimina de la lista local
    }

    public void actualizarProducto(int id, String nuevoNombre, double nuevoPrecio, String nuevaDescripcion) {
        Producto.actualizarProducto(id, nuevoNombre, nuevoPrecio, nuevaDescripcion); // Actualiza en la base de datos
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                producto.setNombre(nuevoNombre);
                producto.setPrecio(nuevoPrecio);
                producto.setDescripcion(nuevaDescripcion);
            }
        }
    }

    public List<Producto> obtenerProductos() {
        return productos;
    }
}