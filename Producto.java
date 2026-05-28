package fianl;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    private String nombre;
    private double precio;
    private int stock;
    private List<Proveedor> proveedores; 

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.proveedores = new ArrayList<>();
    }

    public void agregarProveedor(Proveedor proveedor) {
        this.proveedores.add(proveedor);
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    
    public void reducirStock(int cantidad) {
        this.stock -= cantidad;
    }
}