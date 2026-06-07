package model;

import patron.EstrategiaDescuento;
import patron.SinDescuento;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class Venta {
    private int id;
    private Cliente cliente;
    private Date fecha;
    private List<ItemVenta> items;  // Una lista que guarda todos los artículos de esta venta
    private double total;

    // Atributo para la estrategia de descuento
    private EstrategiaDescuento estrategiaDescuento;

    public Venta() {
        this.items = new ArrayList<>();
        this.fecha = new Date();// Por defecto, se crea con la fecha actual por si no me acuerdo

        //Por defecto, una venta no tiene descuentos
        this.estrategiaDescuento = new SinDescuento();
    }

    public Venta(int id, Cliente cliente) {
        this();
        this.id = id;
        this.cliente = cliente;
    }

    //  para cambiar el tipo de descuento en tiempo de ejecución
    public void setEstrategiaDescuento(EstrategiaDescuento estrategia) {
        this.estrategiaDescuento = estrategia;
        calcularTotal();
    }

    // agregar ítems a la venta y actualizar el total automáticamente
    public void agregarItem(ItemVenta item) {
        this.items.add(item);
        calcularTotal();
    }

    //  Cálculo del total integrando el patrón de diseño
    public void calcularTotal() {
        double sumaSubtotales = 0;
        for (ItemVenta item : items) {
            sumaSubtotales += item.getSubtotal();
        }

        // se Agrega la estrategia de descuento al subtotal
        double subtotalConDescuento = estrategiaDescuento.aplicarDescuento(sumaSubtotales);

        //  se Agrega el 19% de IVA al valor ya descontado
        this.total = subtotalConDescuento * 1.19;
    }

    //  Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public List<ItemVenta> getItems() { return items; }
    public double getTotal() { return total; }
}
