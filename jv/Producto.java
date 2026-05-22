package proyectojava2.jv;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    private String nombre;
    private double precioMenudeo;
    private double precioMayoreo;
    private int cantidadParaMayoreo;
    private int stock;
    private List<Proveedor> proveedores;

    public Producto(String nombre, double precioMenudeo, double precioMayoreo, int cantidadParaMayoreo, int stock) {
        this.nombre = nombre;
        this.precioMenudeo = precioMenudeo;
        this.precioMayoreo = precioMayoreo;
        this.cantidadParaMayoreo = cantidadParaMayoreo;
        this.stock = stock;
        this.proveedores = new ArrayList<>();
    }

    public void agregarProveedor(Proveedor proveedor) {
        this.proveedores.add(proveedor);
    }

    public double getPrecioAplicable(int cantidadSolicitada) {
        if (cantidadSolicitada >= cantidadParaMayoreo) {
            return precioMayoreo;
        }
        return precioMenudeo;
    }

    public void reducirStock(int cantidad) {
        if (this.stock >= cantidad) {
            this.stock -= cantidad;
            verificarAlertaStock();
        }
    }

    private void verificarAlertaStock() {
        if (this.stock <= 5) {
            System.out.println("ALERTA: El producto '" + this.nombre + "' tiene poco stock (" + this.stock + " unidades).");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }
}