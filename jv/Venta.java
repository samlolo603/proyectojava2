package project.proyectojava2main.jv;

import java.util.ArrayList;
import java.util.List;

public class Venta {
    private int id;
    private Cliente cliente;
    private List<DetalleVenta> detalles;
    private double total;

    public Venta(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarDetalle(Producto producto, int cantidad) {
        DetalleVenta nuevoDetalle = new DetalleVenta(producto, cantidad);
        detalles.add(nuevoDetalle);
        producto.reducirStock(cantidad);
        calcularTotal();
    }

    private void calcularTotal() {
        this.total = 0.0;
        int indice = 0;
        
        while (indice < detalles.size()) {
            this.total += detalles.get(indice).calcularSubtotal();
            indice++;
        }
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public double getTotal() {
        return total;
    }
}