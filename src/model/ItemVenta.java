package model;

public class ItemVenta {
  
    private int id;
    private Celular celular; // Relación: Un ítem tiene un celular asociado
    private int cantidad;
    private double subtotal;

    public ItemVenta() {}

    // Constructor
    public ItemVenta(int id, Celular celular, int cantidad) {
        this.id = id;
        this.celular = celular;
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    //  calcula el subtotal multiplicando precio por cantidad
    public double calcularSubtotal() {
        if (celular != null) {
            return celular.getPrecio() * cantidad;
        }
        return 0.0;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Celular getCelular() { return celular; }
    public void setCelular(Celular celular) {
        this.celular = celular;
        this.subtotal = calcularSubtotal(); // Recalcular si cambia el celular
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal(); // Recalcular si cambia la cantidad
    }

    public double getSubtotal() { return subtotal; }
    // No ponemos setSubtotal porque se calcula automáticamente para evitar errores humanos.

}
