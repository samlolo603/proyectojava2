package project.proyectojava2main.jv;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorMiniMarket {
    private static final int OPCION_REGISTRAR_PROVEEDOR = 1;
    private static final int OPCION_REGISTRAR_PRODUCTO = 2;
    private static final int OPCION_REGISTRAR_CLIENTE = 3;
    private static final int OPCION_LISTAR_PRODUCTOS = 4;
    private static final int OPCION_BUSCAR_PRODUCTO = 5;
    private static final int OPCION_LISTAR_CLIENTES = 6;
    private static final int OPCION_LISTAR_PROVEEDORES = 7;
    private static final int OPCION_REGISTRAR_VENTA = 8;
    private static final int OPCION_LISTAR_VENTAS = 9;
    private static final int OPCION_SALIR = 10;

    private List<Cliente> clientes;
    private List<Proveedor> proveedores;
    private List<Producto> productos;
    private List<Venta> ventas;
    private Scanner scanner;
    private int contadorVentas;

    public GestorMiniMarket() {
        this.clientes = new ArrayList<>();
        this.proveedores = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.contadorVentas = 1;
    }

    public void iniciar() {
        int opcion = 0;

        while (opcion != OPCION_SALIR) {
            mostrarMenu();
            opcion = leerEntero("Elija una opción: ");

            switch (opcion) {
                case OPCION_REGISTRAR_PROVEEDOR:
                    registrarProveedor();
                    break;
                case OPCION_REGISTRAR_PRODUCTO:
                    registrarProducto();
                    break;
                case OPCION_REGISTRAR_CLIENTE:
                    registrarCliente();
                    break;
                case OPCION_LISTAR_PRODUCTOS:
                    listarProductos();
                    break;
                case OPCION_BUSCAR_PRODUCTO:
                    buscarYMostrarProducto();
                    break;
                case OPCION_LISTAR_CLIENTES:
                    listarClientes();
                    break;
                case OPCION_LISTAR_PROVEEDORES:
                    listarProveedores();
                    break;
                case OPCION_REGISTRAR_VENTA:
                    registrarVenta();
                    break;
                case OPCION_LISTAR_VENTAS:
                    listarVentas();
                    break;
                case OPCION_SALIR:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n--- MENÚ MINIMARKET ---");
        System.out.println("1. Registrar proveedor");
        System.out.println("2. Registrar producto");
        System.out.println("3. Registrar cliente");
        System.out.println("4. Listar productos");
        System.out.println("5. Consultar producto por nombre");
        System.out.println("6. Listar clientes");
        System.out.println("7. Listar proveedores");
        System.out.println("8. Registrar venta");
        System.out.println("9. Listar ventas");
        System.out.println("10. Salir");
    }

    private void registrarProveedor() {
        System.out.println("\n--- REGISTRAR PROVEEDOR ---");
        String nombre = leerTexto("Nombre del proveedor: ");

        if (buscarProveedorPorNombre(nombre) != null) {
            System.out.println("Error: El proveedor ya existe.");
            return;
        }

        String telefono = leerTexto("Teléfono: ");
        String direccion = leerTexto("Dirección: ");
        String correo = leerTexto("Correo: ");

        Proveedor nuevo = new Proveedor(nombre, telefono, direccion, correo);
        proveedores.add(nuevo);
        System.out.println("Proveedor registrado con éxito.");
    }

    private void registrarProducto() {
        System.out.println("\n--- REGISTRAR PRODUCTO ---");
        String nombre = leerTexto("Nombre del producto: ");

        if (buscarProductoPorNombre(nombre) != null) {
            System.out.println("Error: El producto ya existe.");
            return;
        }

        double precioMenudeo = leerDouble("Precio menudeo: ");
        double precioMayoreo = leerDouble("Precio mayoreo: ");
        int cantMayoreo = leerEntero("Cantidad mínima para mayoreo: ");
        int stock = leerEntero("Stock inicial: ");

        Producto nuevo = new Producto(nombre, precioMenudeo, precioMayoreo, cantMayoreo, stock);

        if (!proveedores.isEmpty()) {
            String respuesta = leerTexto("¿Desea asociar un proveedor? (s/n): ");
            if (respuesta.equalsIgnoreCase("s")) {
                listarProveedores();
                int idx = leerEntero("Seleccione el número del proveedor: ") - 1;
                if (idx >= 0 && idx < proveedores.size()) {
                    nuevo.agregarProveedor(proveedores.get(idx));
                    System.out.println("Proveedor asociado al producto.");
                } else {
                    System.out.println("Proveedor no válido.");
                }
            }
        }

        productos.add(nuevo);
        System.out.println("Producto registrado con éxito.");
    }

    private void registrarCliente() {
        System.out.println("\n--- REGISTRAR CLIENTE ---");
        String nombre = leerTexto("Nombre del cliente: ");

        if (buscarClientePorNombre(nombre) != null) {
            System.out.println("Error: El cliente ya existe.");
            return;
        }

        String telefono = leerTexto("Teléfono: ");
        String direccion = leerTexto("Dirección: ");
        String correo = leerTexto("Correo: ");

        Cliente nuevo = new Cliente(nombre, telefono, direccion, correo);
        clientes.add(nuevo);
        System.out.println("Cliente registrado con éxito.");
    }

    private void listarProductos() {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            System.out.printf("%d. %s | Menudeo: $%.2f | Mayoreo: $%.2f (mín %d) | Stock: %d%n",
                i + 1, p.getNombre(), p.getPrecioMenudeo(), p.getPrecioMayoreo(),
                p.getCantidadParaMayoreo(), p.getStock());
        }
    }

    private void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            System.out.printf("%d. %s | Tel: %s | Dir: %s | Correo: %s%n",
                i + 1, c.getNombre(), c.getTelefono(), c.getDireccion(), c.getCorreo());
        }
    }

    private void listarProveedores() {
        System.out.println("\n--- LISTA DE PROVEEDORES ---");
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
            return;
        }
        for (int i = 0; i < proveedores.size(); i++) {
            Proveedor p = proveedores.get(i);
            System.out.printf("%d. %s | Tel: %s | Dir: %s | Correo: %s%n",
                i + 1, p.getNombre(), p.getTelefono(), p.getDireccion(), p.getCorreo());
        }
    }

    private Producto buscarProductoPorNombre(String nombreBuscado) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return p;
            }
        }
        return null;
    }

    private void buscarYMostrarProducto() {
        System.out.println("\n--- BUSCAR PRODUCTO ---");
        String nombre = leerTexto("Ingrese el nombre del producto: ");
        Producto prod = buscarProductoPorNombre(nombre);

        if (prod != null) {
            System.out.printf("Producto: %s | Menudeo: $%.2f | Mayoreo: $%.2f (mín %d) | Stock: %d%n",
                prod.getNombre(), prod.getPrecioMenudeo(), prod.getPrecioMayoreo(),
                prod.getCantidadParaMayoreo(), prod.getStock());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private Proveedor buscarProveedorPorNombre(String nombreBuscado) {
        for (Proveedor p : proveedores) {
            if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return p;
            }
        }
        return null;
    }

    private Cliente buscarClientePorNombre(String nombreBuscado) {
        for (Cliente c : clientes) {
            if (c.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return c;
            }
        }
        return null;
    }

    private void registrarVenta() {
        System.out.println("\n--- REGISTRAR VENTA ---");

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados. Registre un cliente primero.");
            return;
        }
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados. Registre un producto primero.");
            return;
        }

        listarClientes();
        int idxCliente = leerEntero("Seleccione el cliente (número): ") - 1;
        if (idxCliente < 0 || idxCliente >= clientes.size()) {
            System.out.println("Cliente no válido.");
            return;
        }
        Cliente cliente = clientes.get(idxCliente);

        List<Producto> prodTemp = new ArrayList<>();
        List<Integer> cantTemp = new ArrayList<>();
        double total = 0;

        boolean agregarMas = true;
        while (agregarMas) {
            listarProductos();
            int idxProd = leerEntero("Seleccione el producto (número): ") - 1;
            if (idxProd < 0 || idxProd >= productos.size()) {
                System.out.println("Producto no válido.");
                continue;
            }
            Producto producto = productos.get(idxProd);

            int cantidad = leerEntero("Cantidad: ");
            if (cantidad <= 0) {
                System.out.println("Cantidad debe ser mayor a cero.");
                continue;
            }
            if (cantidad > producto.getStock()) {
                System.out.println("Stock insuficiente. Stock disponible: " + producto.getStock());
                continue;
            }

            prodTemp.add(producto);
            cantTemp.add(cantidad);
            total += producto.getPrecioAplicable(cantidad) * cantidad;
            System.out.printf("Agregado: %s x%d = $%.2f%n",
                producto.getNombre(), cantidad,
                producto.getPrecioAplicable(cantidad) * cantidad);

            String resp = leerTexto("¿Agregar otro producto? (s/n): ");
            agregarMas = resp.equalsIgnoreCase("s");
        }

        System.out.printf("Total de la venta: $%.2f%n", total);
        String confirmar = leerTexto("¿Confirmar venta? (s/n): ");
        if (confirmar.equalsIgnoreCase("s")) {
            Venta venta = new Venta(contadorVentas++, cliente);
            for (int i = 0; i < prodTemp.size(); i++) {
                venta.agregarDetalle(prodTemp.get(i), cantTemp.get(i));
            }
            ventas.add(venta);
            System.out.println("Venta registrada con éxito.");
        } else {
            System.out.println("Venta cancelada.");
        }
    }

    private void listarVentas() {
        System.out.println("\n--- LISTA DE VENTAS ---");
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        for (Venta v : ventas) {
            System.out.printf("Venta #%d | Cliente: %s | Total: $%.2f%n",
                v.getId(), v.getCliente().getNombre(), v.getTotal());
            for (DetalleVenta d : v.getDetalles()) {
                System.out.printf("   %s x%d = $%.2f%n",
                    d.getProducto().getNombre(), d.getCantidad(), d.calcularSubtotal());
            }
        }
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número.");
            }
        }
    }

    private String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Entrada inválida. El campo no puede estar vacío.");
        }
    }

    public static void main(String[] args) {
        GestorMiniMarket sistema = new GestorMiniMarket();
        sistema.iniciar();
    }
}
