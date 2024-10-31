package Controladores;
import java.util.ArrayList;
import java.util.List;
import Modelos.ItemCarrito;
import java.util.Scanner;

public class ItemCarritoController {
    private List<ItemCarrito> itemsCarrito;
    private Scanner scanner;

    public ItemCarritoController() {
    	this.itemsCarrito = new ArrayList<>();
    	this.scanner = new Scanner(System.in);
    }
    
    public List<ItemCarrito> obtenerItemsCarrito()
    {
    	return itemsCarrito;
    }

    public void agregarItemCarrito(int id, int carritoId, int productoId, int cantidad, double subtotal) {
        ItemCarrito.agregarItemCarrito(id, carritoId, productoId, cantidad, subtotal); // Agrega en la base de datos
        itemsCarrito.add(new ItemCarrito(id, carritoId, productoId, cantidad, subtotal)); // Agrega a la lista en memoria
    }

    public void eliminarItemCarrito(int id) {
        ItemCarrito.eliminarItemCarrito(id); // Elimina en la base de datos
        itemsCarrito.removeIf(item -> item.getId() == id); // Elimina de la lista en memoria
    }

    public void actualizarItemCarrito(int id, int nuevaCantidad, double nuevoSubtotal) {
        ItemCarrito.actualizarItemCarrito(id, nuevaCantidad, nuevoSubtotal); // Actualiza en la base de datos
        for (ItemCarrito item : itemsCarrito) {
            if (item.getId() == id) {
                item.setCantidad(nuevaCantidad);
                item.setSubtotal(nuevoSubtotal);
            }
        }
    }    
}