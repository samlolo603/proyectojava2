@startuml
abstract class Persona {
  # nombre: String
  # telefono: String
  # direccion: String
  # correo: String
  + Persona(nombre, telefono, direccion, correo)
  + getNombre(): String
  + getTelefono(): String
  + getDireccion(): String
  + getCorreo(): String
}

class Cliente {
  + Cliente(nombre, telefono, direccion, correo)
}

class Proveedor {
  + Proveedor(nombre, telefono, direccion, correo)
}

class Producto {
  - {static} UMBRAL_STOCK_BAJO: int
  - nombre: String
  - precioMenudeo: double
  - precioMayoreo: double
  - cantidadParaMayoreo: int
  - stock: int
  - proveedores: List<Proveedor>
  + Producto(nombre, precioMenudeo, precioMayoreo, cantidadParaMayoreo, stock)
  + agregarProveedor(proveedor: Proveedor): void
  + getPrecioAplicable(cantidadSolicitada: int): double
  + reducirStock(cantidad: int): void
  - verificarAlertaStock(): void
  + getNombre(): String
  + getPrecioMenudeo(): double
  + getPrecioMayoreo(): double
  + getCantidadParaMayoreo(): int
  + getStock(): int
  + getProveedores(): List<Proveedor>
}

class DetalleVenta {
  - producto: Producto
  - cantidad: int
  + DetalleVenta(producto, cantidad)
  + getProducto(): Producto
  + getCantidad(): int
  + calcularSubtotal(): double
}

class Venta {
  - id: int
  - cliente: Cliente
  - detalles: List<DetalleVenta>
  - total: double
  + Venta(id, cliente)
  + agregarDetalle(producto, cantidad): void
  - calcularTotal(): void
  + getId(): int
  + getCliente(): Cliente
  + getDetalles(): List<DetalleVenta>
  + getTotal(): double
}

class GestorMiniMarket {
  - {static} OPCION_REGISTRAR_PROVEEDOR: int
  - {static} OPCION_REGISTRAR_PRODUCTO: int
  - {static} OPCION_REGISTRAR_CLIENTE: int
  - {static} OPCION_LISTAR_PRODUCTOS: int
  - {static} OPCION_BUSCAR_PRODUCTO: int
  - {static} OPCION_LISTAR_CLIENTES: int
  - {static} OPCION_LISTAR_PROVEEDORES: int
  - {static} OPCION_REGISTRAR_VENTA: int
  - {static} OPCION_LISTAR_VENTAS: int
  - {static} OPCION_SALIR: int
  - clientes: List<Cliente>
  - proveedores: List<Proveedor>
  - productos: List<Producto>
  - ventas: List<Venta>
  - scanner: Scanner
  - contadorVentas: int
  + GestorMiniMarket()
  + iniciar(): void
  - mostrarMenu(): void
  - registrarProveedor(): void
  - registrarProducto(): void
  - registrarCliente(): void
  - listarProductos(): void
  - listarClientes(): void
  - listarProveedores(): void
  - buscarProductoPorNombre(nombreBuscado: String): Producto
  - buscarYMostrarProducto(): void
  - buscarProveedorPorNombre(nombreBuscado: String): Proveedor
  - buscarClientePorNombre(nombreBuscado: String): Cliente
  - registrarVenta(): void
  - listarVentas(): void
  - leerEntero(mensaje: String): int
  - leerDouble(mensaje: String): double
  - leerTexto(mensaje: String): String
  + {static} main(args: String[]): void
}

Persona <|-- Cliente
Persona <|-- Proveedor
Producto "1" o-- "*" Proveedor : "abastecido por"
Venta "1" *-- "*" DetalleVenta
DetalleVenta "1" o-- "1" Producto
Venta "1" o-- "1" Cliente
GestorMiniMarket "1" o-- "*" Cliente
GestorMiniMarket "1" o-- "*" Proveedor
GestorMiniMarket "1" o-- "*" Producto
GestorMiniMarket "1" o-- "*" Venta
@enduml
