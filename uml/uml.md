@startuml
skinparam classAttributeIconSize 0
skinparam shadowing false
skinparam class {
    BackgroundColor White
    ArrowColor #2C3E50
    BorderColor #2C3E50
}

class Persona {
    # nombre : String
    # telefono : String
    # direccion : String
    # correo : String
    + Persona(nombre: String, telefono: String, direccion: String, correo: String)
    + getNombre() : String
    + getTelefono() : String
    + getDireccion() : String
    + getCorreo() : String
}

class Cliente {
    + Cliente(nombre: String, telefono: String, direccion: String, correo: String)
}

class Proveedor {
    + Proveedor(nombre: String, telefono: String, direccion: String, correo: String)
}

class Producto {
    - nombre : String
    - precio : double
    - stock : int
    - proveedores : List<Proveedor>
    + Producto(nombre: String, precio: double, stock: int)
    + agregarProveedor(proveedor: Proveedor) : void
    + getNombre() : String
    + getPrecio() : double
    + getStock() : int
    + reducirStock(cantidad: int) : void
}

class DetalleVenta {
    - producto : Producto
    - cantidad : int
    - subtotal : double
    + DetalleVenta(producto: Producto, cantidad: int)
    + getProducto() : Producto
    + getCantidad() : int
    + getSubtotal() : double
}

class Venta {
    - idVenta : int
    - cliente : Cliente
    - detalles : List<DetalleVenta>
    - total : double
    + Venta(idVenta: int, cliente: Cliente)
    + agregarDetalle(detalle: DetalleVenta) : void
    + getIdVenta() : int
    + getCliente() : Cliente
    + getTotal() : double
    + getDetalles() : List<DetalleVenta>
}

class MiniMarket {
    - proveedores : List<Proveedor>
    - productos : List<Producto>
    - clientes : List<Cliente>
    - ventas : List<Venta>
    - contadorVentas : int
    + {static} main(args: String[]) : void
    + iniciar() : void
    - registrarProveedor(scanner: Scanner) : void
    - registrarProducto(scanner: Scanner) : void
    - registrarCliente(scanner: Scanner) : void
    - crearVenta(scanner: Scanner) : void
    - buscarProveedorObjeto(nombre: String) : Proveedor
    - buscarProductoObjeto(nombre: String) : Producto
    - buscarClienteObjeto(nombre: String) : Cliente
    - consultarProducto(scanner: Scanner) : void
    - consultarCliente(scanner: Scanner) : void
    - consultarProveedor(scanner: Scanner) : void
    - buscarVenta(scanner: Scanner) : void
    - consultarInventario(scanner: Scanner) : void
}

' Relaciones de Herencia
Persona <|-- Cliente
Persona <|-- Proveedor

' Relaciones de Asociación y Composición
Producto "1" --> "*" Proveedor : surtido por
Venta "*" --> "1" Cliente : pertenece a
Venta "1" *-- "*" DetalleVenta : contiene
DetalleVenta "*" --> "1" Producto : referencia

' Relaciones de Gestión en la clase principal
MiniMarket o-- "*" Proveedor
MiniMarket o-- "*" Producto
MiniMarket o-- "*" Cliente
MiniMarket o-- "*" Venta

@endum