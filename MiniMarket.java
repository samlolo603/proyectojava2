package fianl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MiniMarket {
    private List<Proveedor> proveedores = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Venta> ventas = new ArrayList<>();
    private int contadorVentas = 1; 

    public static void main(String[] args) {
        MiniMarket sistema = new MiniMarket();
        sistema.iniciar();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 10) {
            System.out.println("\n--- SISTEMA MINIMARKET ---");
            System.out.println("1. Registrar proveedores");
            System.out.println("2. Registrar productos");
            System.out.println("3. Registrar clientes");
            System.out.println("4. Crear ventas con múltiples productos");
            System.out.println("5. Consultar producto por nombre");
            System.out.println("6. Consultar cliente por nombre");
            System.out.println("7. Consultar proveedor por nombre");
            System.out.println("8. Buscar venta por ID");
            System.out.println("9. Consultar inventario de un producto");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1: registrarProveedor(scanner); break;
                case 2: registrarProducto(scanner); break;
                case 3: registrarCliente(scanner); break;
                case 4: crearVenta(scanner); break;
                case 5: consultarProducto(scanner); break;
                case 6: consultarCliente(scanner); break;
                case 7: consultarProveedor(scanner); break;
                case 8: buscarVenta(scanner); break;
                case 9: consultarInventario(scanner); break;
                case 10: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    private void registrarProveedor(Scanner scanner) {
        System.out.print("Nombre del proveedor: ");
        String nombre = scanner.nextLine();
        
        if (buscarProveedorObjeto(nombre) == null) {
            System.out.print("Teléfono: "); String tel = scanner.nextLine();
            System.out.print("Dirección: "); String dir = scanner.nextLine();
            System.out.print("Correo: "); String correo = scanner.nextLine();
            proveedores.add(new Proveedor(nombre, tel, dir, correo));
            System.out.println("Proveedor registrado con éxito.");
        } else {
            System.out.println("Error: El proveedor ya existe.");
        }
    }

    private void registrarProducto(Scanner scanner) {
        if (proveedores.isEmpty()) {
            System.out.println("Debe registrar al menos un proveedor antes de registrar un producto.");
            return;
        }

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        if (buscarProductoObjeto(nombre) == null) {
            System.out.print("Precio unitario: "); double precio = scanner.nextDouble();
            System.out.print("Stock inicial: "); int stock = scanner.nextInt();
            scanner.nextLine(); 

            Producto nuevoProducto = new Producto(nombre, precio, stock);
            
            System.out.print("Ingrese nombre del proveedor a asociar: ");
            String nombreProv = scanner.nextLine();
            Proveedor prov = buscarProveedorObjeto(nombreProv);
            
            if (prov != null) {
                nuevoProducto.agregarProveedor(prov);
                productos.add(nuevoProducto);
                System.out.println("Producto registrado.");
            } else {
                System.out.println("Proveedor no encontrado. Registro cancelado.");
            }
        } else {
            System.out.println("Error: El producto ya existe.");
        }
    }

    private void registrarCliente(Scanner scanner) {
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();

        if (buscarClienteObjeto(nombre) == null) {
            System.out.print("Teléfono: "); String tel = scanner.nextLine();
            System.out.print("Dirección: "); String dir = scanner.nextLine();
            System.out.print("Correo: "); String correo = scanner.nextLine();
            clientes.add(new Cliente(nombre, tel, dir, correo));
            System.out.println("Cliente registrado.");
        } else {
            System.out.println("Error: El cliente ya existe.");
        }
    }

    private void crearVenta(Scanner scanner) {
        if (productos.isEmpty() || clientes.isEmpty()) {
            System.out.println("Error: Debe existir al menos un producto y un cliente registrados.");
            return;
        }

        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = scanner.nextLine();
        Cliente cliente = buscarClienteObjeto(nombreCliente);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        Venta nuevaVenta = new Venta(contadorVentas++, cliente);
        int agregarMas = 1;

        while (agregarMas == 1) {
            System.out.print("Ingrese nombre del producto: ");
            String nombreProd = scanner.nextLine();
            Producto producto = buscarProductoObjeto(nombreProd);

            if (producto != null) {
                System.out.print("Cantidad a vender: ");
                int cantidad = scanner.nextInt();
                scanner.nextLine();

                if (producto.getStock() >= cantidad) {
                    producto.reducirStock(cantidad);
                    nuevaVenta.agregarDetalle(new DetalleVenta(producto, cantidad));
                    System.out.println("Producto agregado a la venta.");
                    
                    if (producto.getStock() <= 5) {
                        System.out.println("¡ALERTA! Queda poco producto en inventario (" + producto.getStock() + " unidades restantes).");
                    }
                } else {
                    System.out.println("Stock insuficiente.");
                }
            } else {
                System.out.println("Producto no encontrado.");
            }

            System.out.print("¿Desea agregar otro producto? (1 = Sí, 0 = No): ");
            agregarMas = scanner.nextInt();
            scanner.nextLine();
        }

        if (!nuevaVenta.getDetalles().isEmpty()) {
            ventas.add(nuevaVenta);
            System.out.println("Venta registrada. Total a pagar: $" + nuevaVenta.getTotal());
        }
    }

    
    private Proveedor buscarProveedorObjeto(String nombre) {
        int i = 0;
        Proveedor encontrado = null;
        while (i < proveedores.size() && encontrado == null) {
            if (proveedores.get(i).getNombre().equalsIgnoreCase(nombre)) {
                encontrado = proveedores.get(i);
            }
            i++;
        }
        return encontrado;
    }

    private Producto buscarProductoObjeto(String nombre) {
        int i = 0;
        Producto encontrado = null;
        while (i < productos.size() && encontrado == null) {
            if (productos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                encontrado = productos.get(i);
            }
            i++;
        }
        return encontrado;
    }

    private Cliente buscarClienteObjeto(String nombre) {
        int i = 0;
        Cliente encontrado = null;
        while (i < clientes.size() && encontrado == null) {
            if (clientes.get(i).getNombre().equalsIgnoreCase(nombre)) {
                encontrado = clientes.get(i);
            }
            i++;
        }
        return encontrado;
    }

    private void consultarProducto(Scanner scanner) {
        System.out.print("Nombre del producto a buscar: ");
        Producto p = buscarProductoObjeto(scanner.nextLine());
        if (p != null) System.out.println("Producto: " + p.getNombre() + " | Precio: $" + p.getPrecio() + " | Stock: " + p.getStock());
        else System.out.println("No encontrado.");
    }

    private void consultarCliente(Scanner scanner) {
        System.out.print("Nombre del cliente a buscar: ");
        Cliente c = buscarClienteObjeto(scanner.nextLine());
        if (c != null) System.out.println("Cliente: " + c.getNombre() + " | Tel: " + c.getTelefono());
        else System.out.println("No encontrado.");
    }

    private void consultarProveedor(Scanner scanner) {
        System.out.print("Nombre del proveedor a buscar: ");
        Proveedor p = buscarProveedorObjeto(scanner.nextLine());
        if (p != null) System.out.println("Proveedor: " + p.getNombre() + " | Empresa: " + p.getNombre());
        else System.out.println("No encontrado.");
    }

    private void buscarVenta(Scanner scanner) {
        System.out.print("ID de la venta a buscar: ");
        int id = scanner.nextInt();
        int i = 0;
        Venta encontrada = null;
        
        while (i < ventas.size() && encontrada == null) {
            if (ventas.get(i).getIdVenta() == id) {
                encontrada = ventas.get(i);
            }
            i++;
        }

        if (encontrada != null) {
            System.out.println("Venta #" + encontrada.getIdVenta() + " | Cliente: " + encontrada.getCliente().getNombre() + " | Total: $" + encontrada.getTotal());
        } else {
            System.out.println("Venta no encontrada.");
        }
    }

    private void consultarInventario(Scanner scanner) {
        System.out.print("Nombre del producto para ver stock: ");
        Producto p = buscarProductoObjeto(scanner.nextLine());
        if (p != null) {
            System.out.println("El inventario actual de " + p.getNombre() + " es: " + p.getStock() + " unidades.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
}