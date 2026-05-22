package proyectojava2.jv;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorMiniMarket {
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
        
        while (opcion != 10) {
            mostrarMenu();
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    registrarProveedor();
                    break;
                case 2:
                    registrarProducto();
                    break;
                case 5:
                    buscarYMostrarProducto();
                    break;
                case 10:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida o en construcción.");
                    break;
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n--- MENÚ MINIMARKET ---");
        System.out.println("1. Registrar proveedores.");
        System.out.println("2. Registrar productos.");
        System.out.println("5. Consultar producto por nombre.");
        System.out.println("10. Salir");
        System.out.print("Elija una opción: ");
    }

    private void registrarProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        if (buscarProductoPorNombre(nombre) != null) {
            System.out.println("Error: El producto ya existe.");
        } else {
            System.out.print("Precio Menudeo: ");
            double precioMenudeo = Double.parseDouble(scanner.nextLine());
            System.out.print("Precio Mayoreo: ");
            double precioMayoreo = Double.parseDouble(scanner.nextLine());
            System.out.print("Cantidad mínima para aplicar mayoreo: ");
            int cantMayoreo = Integer.parseInt(scanner.nextLine());
            System.out.print("Stock inicial: ");
            int stock = Integer.parseInt(scanner.nextLine());

            Producto nuevoProducto = new Producto(nombre, precioMenudeo, precioMayoreo, cantMayoreo, stock);
            productos.add(nuevoProducto);
            System.out.println("Producto registrado con éxito.");
        }
    }

    private Producto buscarProductoPorNombre(String nombreBuscado) {
        int indice = 0;
        Producto productoEncontrado = null;

        while (indice < productos.size() && productoEncontrado == null) {
            if (productos.get(indice).getNombre().equalsIgnoreCase(nombreBuscado)) {
                productoEncontrado = productos.get(indice);
            }
            indice++;
        }
        return productoEncontrado;
    }

    private void buscarYMostrarProducto() {
        System.out.print("Ingrese el nombre del producto a buscar: ");
        String nombre = scanner.nextLine();
        Producto prod = buscarProductoPorNombre(nombre);

        if (prod != null) {
            System.out.println("Producto encontrado: " + prod.getNombre() + " | Stock: " + prod.getStock());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void registrarProveedor() {
        System.out.println("Registrando proveedor (Implementación pendiente)...");
    }

    public static void main(String[] args) {
        GestorMiniMarket sistema = new GestorMiniMarket();
        sistema.iniciar();
    }
}