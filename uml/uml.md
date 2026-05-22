@startuml
abstract class Persona {
  # nombre: String
  # telefono: String
  # direccion: String
  # correo: String
  + Persona(nombre, telefono, direccion, correo)
  + getNombre(): String
}

class Cliente {
  + Cliente(nombre, telefono, direccion, correo)
}

class Proveedor {
  + Proveedor(nombre, telefono, direccion, correo)
}

class Producto {
  - nombre: String
  - precioMenudeo: double
  - precioMayoreo: double
  - cantidadParaMayoreo: int
  - stock: int
  - proveedores: List<Proveedor>
  + Producto(nombre, precioMenudeo, precioMayoreo, cantidadParaMayoreo, stock)
  + agregarProveedor(proveedor: Proveedor): void
  + reducirStock(cantidad: int): void
  + verificarAlertaStock(): void
  + getPrecioAplicable(cantidad: int): double
}

class DetalleVenta {
  - producto: Producto
  - cantidad: int
  + calcularSubtotal(): double
}

class Venta {
  - id: int
  - cliente: Cliente
  - detalles: List<DetalleVenta>
  - total: double
  + agregarDetalle(producto, cantidad): void
  + calcularTotal(): void
}

class GestorMiniMarket {
  - clientes: List<Cliente>
  - proveedores: List<Proveedor>
  - productos: List<Producto>
  - ventas: List<Venta>
  + iniciar(): void
  - registrarProducto(): void
  - buscarProducto(nombre: String): Producto
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