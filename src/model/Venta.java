package model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class Venta {
    private int id;
    private Cliente cliente; // Relación: La venta pertenece a un cliente
    private Date fecha;
    private List<ItemVenta> items; // Una lista que guarda todos los artículos de esta venta
    private double total;

    public Venta() {
        this.items = new ArrayList<>();
        this.fecha = new Date(); // Por defecto, se crea con la fecha actual
    }

    public Venta(int id, Cliente cliente) {
        this();
        this.id = id;
        this.cliente = cliente;
    }

    // agregar ítems a la venta y actualizar el total automáticamente
    public void agregarItem(ItemVenta item) {
        this.items.add(item);
        calcularTotal();
    }

    // Calcula el total sumando los subtotales e incrementando el 19% de IVA
    public void calcularTotal() {
        double sumaSubtotales = 0;
        for (ItemVenta item : items) {
            sumaSubtotales += item.getSubtotal();
        }
        // Agregamos el 19% de IVA: Total = base * 1.19
        this.total = sumaSubtotales * 1.19;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public List<ItemVenta> getItems() { return items; }

    public double getTotal() { return total; }
    // El total no lleva setter directo porque depende de calcularTotal()
}
