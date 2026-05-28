package fianl;

import java.util.ArrayList;
import java.util.List;

public class Venta {
    private int idVenta;
    private Cliente cliente;
    private List<DetalleVenta> detalles;
    private double total;

    public Venta(int idVenta, Cliente cliente) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        this.detalles.add(detalle);
        this.total += detalle.getSubtotal();
    }

    public int getIdVenta() { return idVenta; }
    public Cliente getCliente() { return cliente; }
    public double getTotal() { return total; }
    public List<DetalleVenta> getDetalles() { return detalles; }
}