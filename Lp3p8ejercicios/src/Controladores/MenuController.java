package Controladores;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Modelos.Orden;

public class MenuController {
    private GestorDatos gestorDatos;
    private Scanner scanner;

    public MenuController(GestorDatos gestorDatos) {
        this.gestorDatos = gestorDatos;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() throws SQLException {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Mostrar Clientes");
            System.out.println("3. Actualizar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Agregar Producto");
            System.out.println("6. Mostrar Productos");
            System.out.println("7. Actualizar Producto");
            System.out.println("8. Eliminar Producto");
            System.out.println("9. Agregar Carrito");
            System.out.println("10. Mostrar Carritos");
            System.out.println("11. Actualizar Carrito");
            System.out.println("12. Eliminar Carrito");
            // Nueva sección para ItemCarrito
            System.out.println("13. Agregar Item al Carrito");
            System.out.println("14. Mostrar Items del Carrito");
            System.out.println("15. Actualizar Item del Carrito");
            System.out.println("16. Eliminar Item del Carrito");
            // Fin de la sección para ItemCarrito
            System.out.println("17. Agregar Orden");
            System.out.println("18. Mostrar Ordenes");
            System.out.println("19. Actualizar Orden");
            System.out.println("20. Eliminar Orden");
            System.out.println("21. Agregar Pedido");
            System.out.println("22. Mostrar Pedidos");
            System.out.println("23. Actualizar Pedido");
            System.out.println("24. Eliminar Pedido");
            System.out.println("25. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1 -> agregarCliente();
                case 2 -> gestorDatos.mostrarClientes();
                case 3 -> actualizarCliente();
                case 4 -> eliminarCliente();
                case 5 -> agregarProducto();
                case 6 -> gestorDatos.mostrarProductos();
                case 7 -> actualizarProducto();
                case 8 -> eliminarProducto();
                case 9 -> agregarCarrito();
                case 10 -> gestorDatos.mostrarCarritos();
                case 11 -> actualizarCarrito();
                case 12 -> eliminarCarrito();
                // Opciones para ItemCarrito
                case 13 -> agregarItemCarrito();
                case 14 -> gestorDatos.mostrarItemsCarrito();
                case 15 -> actualizarItemCarrito();
                case 16 -> eliminarItemCarrito();
                // Fin de las opciones para ItemCarrito
                case 17 -> agregarOrden();
                case 18 -> gestorDatos.mostrarOrdenes();
                case 19 -> actualizarOrden();
                case 20 -> eliminarOrden();
                case 21 -> agregarPedido();
                case 22 -> gestorDatos.mostrarPedidos();
                case 23 -> actualizarPedido();
                case 24 -> eliminarPedido();
                case 25 -> continuar = false;
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    // Métodos CRUD para Cliente
    private void agregarCliente() throws SQLException {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();
        gestorDatos.agregarCliente(id, nombre, email, clave);
    }

    private void actualizarCliente() throws SQLException {
        System.out.print("ID del cliente a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo Nombre: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Nuevo Email: ");
        String nuevoEmail = scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();
        gestorDatos.actualizarCliente(id, nuevoNombre, nuevoEmail, clave);
    }

    private void eliminarCliente() throws SQLException {
        System.out.print("ID del cliente a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();
        gestorDatos.eliminarCliente(id, clave);
    }

    // Métodos CRUD para Producto
    private void agregarProducto() throws SQLException {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();
        gestorDatos.agregarProducto(id, nombre, precio, descripcion, clave);
    }

    private void actualizarProducto() throws SQLException {
        System.out.print("ID del producto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo Nombre: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Nuevo Precio: ");
        double nuevoPrecio = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Nueva Descripción: ");
        String nuevaDescripcion = scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();
        gestorDatos.actualizarProducto(id, nuevoNombre, nuevoPrecio, nuevaDescripcion, clave);
    }

    private void eliminarProducto() throws SQLException {
        System.out.print("ID del producto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();
        gestorDatos.eliminarProducto(id, clave);
    }

    // Métodos CRUD para Carrito
    private void agregarCarrito() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID del carrito: ");
        int idCarrito = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("ID del cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Fecha de Creación (en formato yyyy-MM-dd): ");
        String fechaStr = scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();

        try {
            // Convertir la fecha ingresada en milisegundos
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatoFecha.parse(fechaStr);
            long fechaEnMilisegundos = fecha.getTime();

            // Continúa con el flujo de agregar carrito usando `fechaEnMilisegundos`
            System.out.println("Fecha en milisegundos: " + fechaEnMilisegundos);

            gestorDatos.agregarCarrito(idCarrito, idCliente, fecha, clave);
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido. Asegúrate de usar yyyy-MM-dd.");
        }
    }

    private void actualizarCarrito() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID del carrito a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Nuevo ID del Cliente: ");
        int nuevoClienteId = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Nueva Fecha de Creación (en formato yyyy-MM-dd): ");
        String fechaStr = scanner.nextLine();

        System.out.print("Clave: ");
        String clave = scanner.nextLine();

        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date nuevaFechaCreacion = formatoFecha.parse(fechaStr);
            gestorDatos.actualizarCarrito(id, nuevoClienteId, nuevaFechaCreacion, clave);
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido. Asegúrate de usar yyyy-MM-dd.");
        }
    }


    private void eliminarCarrito() throws SQLException {
        System.out.print("ID del carrito a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();
        gestorDatos.eliminarCarrito(id, clave);
    }

    // Métodos CRUD para ItemCarrito
    private void agregarItemCarrito() throws SQLException {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ID del Carrito: ");
        int carritoId = scanner.nextInt();
        System.out.print("ID del Producto: ");
        int productoId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Subtotal: ");
        double subtotal = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();
        gestorDatos.agregarItemCarrito(id, carritoId, productoId, cantidad, subtotal, clave);
    }

    private void actualizarItemCarrito() throws SQLException {
        System.out.print("ID del Item del Carrito a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nueva Cantidad: ");
        int nuevaCantidad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo Subtotal: ");
        double nuevoSubtotal = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();
        gestorDatos.actualizarItemCarrito(id, nuevaCantidad, nuevoSubtotal, clave);
    }

    private void eliminarItemCarrito() throws SQLException {
        System.out.print("ID del Item del Carrito a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();
        gestorDatos.eliminarItemCarrito(id, clave);
    }

    // Métodos CRUD para Orden
    private void agregarOrden() throws SQLException {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        System.out.print("ID del carrito: ");
        int idCarrito = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        System.out.print("Total: ");
        double total = scanner.nextDouble();
        scanner.nextLine();  // Consumir el salto de línea

        System.out.print("Fecha de Orden (yyyy-MM-dd): ");
        String fechaOrdenStr = scanner.nextLine();
        Date fechaOrden = null;
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaOrden = formatoFecha.parse(fechaOrdenStr);
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido. Asegúrate de usar yyyy-MM-dd.");
            return;
        }

        System.out.print("Clave: ");
        String clave = scanner.nextLine();

        Orden orden = new Orden(id, idCarrito, total, fechaOrden);
        gestorDatos.agregarOrden(orden, clave);
    }

    private void actualizarOrden() throws SQLException {
        System.out.print("ID de Orden a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        System.out.print("Nuevo Total: ");
        double nuevoTotal = scanner.nextDouble();
        scanner.nextLine();  // Consumir el salto de línea

        System.out.print("Nueva Fecha de Orden (yyyy-MM-dd): ");
        String nuevaFechaOrdenStr = scanner.nextLine();
        Date nuevaFechaOrden = null;
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            nuevaFechaOrden = formatoFecha.parse(nuevaFechaOrdenStr);
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido. Asegúrate de usar yyyy-MM-dd.");
            return;
        }

        System.out.print("Clave: ");
        String clave = scanner.nextLine();

        gestorDatos.actualizarOrden(id, nuevoTotal, nuevaFechaOrden, clave);
    }


    private void eliminarOrden() throws SQLException {
        System.out.print("ID de Orden a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        System.out.print("Clave: ");
        String clave = scanner.nextLine();

        gestorDatos.eliminarOrden(id, clave);
    }

    // Métodos CRUD para Pedido
    private void agregarPedido() throws SQLException {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        System.out.print("ID de Orden: ");
        int ordenId = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        System.out.print("Estado: ");
        String estado = scanner.nextLine();

        System.out.print("Fecha de Envío (yyyy-MM-dd): ");
        String fechaEnvioStr = scanner.nextLine();
        Date fechaEnvio = null;
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaEnvio = formatoFecha.parse(fechaEnvioStr);
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido. Asegúrate de usar yyyy-MM-dd.");
            return;
        }

        System.out.print("Dirección de Envío: ");
        String direccionEnvio = scanner.nextLine();

        System.out.print("Clave: ");
        String clave = scanner.nextLine();

        gestorDatos.agregarPedido(id, ordenId, estado, fechaEnvio, direccionEnvio, clave);
    }


    private void actualizarPedido() throws SQLException {
        System.out.print("ID de Pedido a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        System.out.print("Nuevo Estado: ");
        String nuevoEstado = scanner.nextLine();

        System.out.print("Nueva Fecha de Envío (yyyy-MM-dd): ");
        String nuevaFechaEnvioStr = scanner.nextLine();
        Date nuevaFechaEnvio = null;
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            nuevaFechaEnvio = formatoFecha.parse(nuevaFechaEnvioStr);
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido. Asegúrate de usar yyyy-MM-dd.");
            return;
        }

        System.out.print("Nueva Dirección de Envío: ");
        String nuevaDireccionEnvio = scanner.nextLine();

        System.out.print("Clave: ");
        String clave = scanner.nextLine();

        gestorDatos.actualizarPedido(id, nuevoEstado, nuevaFechaEnvio, nuevaDireccionEnvio, clave);
    }

    private void eliminarPedido() throws SQLException {
        System.out.print("ID de Pedido a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        System.out.print("Clave: ");
        String clave = scanner.nextLine();

        gestorDatos.eliminarPedido(id, clave);
    }

}