package Controladores;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Modelos.Orden;

public class OrdenController {
    private List<Orden> ordenes = new ArrayList<>();

    public void agregarOrden(Orden orden) {
        ordenes.add(orden);
    }

    public void eliminarOrden(int id) {
        ordenes.removeIf(orden -> orden.getId() == id);
    }

    public void actualizarOrden(int id, double nuevoTotal, Date nuevaFechaOrden) {
        for (Orden orden : ordenes) {
            if (orden.getId() == id) {
                orden.setTotal(nuevoTotal);
                orden.setFechaOrden(nuevaFechaOrden);
            }
        }
    }

    public List<Orden> obtenerOrdenes() {
        return ordenes;
    }

    public Orden obtenerOrdenPorId(int id) {
        for (Orden orden : ordenes) {
            if (orden.getId() == id) {
                return orden;
            }
        }
        return null; // Retorna null si no se encuentra la orden con el ID especificado
    }
}