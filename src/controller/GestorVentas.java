package controller;


import model.Venta;
import model.ItemVenta;
import persistencia.VentaDAO;
import java.sql.SQLException;

public class GestorVentas {
      private VentaDAO ventaDAO;

    public void VentaControlador() {
        this.ventaDAO = new VentaDAO();
    }

    // Procesar y registrar la venta con validación de stock e IVA
    public String procesarVenta(Venta venta) {
        //  Validar que la venta tenga artículos
        if (venta.getItems() == null || venta.getItems().isEmpty()) {
            return " Error: No se pueden registrar ventas sin productos añadidos.";
        }

        //  Validar existencias (Stock) en el inventario antes de descontar
        for (ItemVenta item : venta.getItems()) {
            if (item.getCantidad() > item.getCelular().getStock()) {
                return " Error: Stock insuficiente para el modelo " + item.getCelular().getModelo() +
                        ". Disponibles: " + item.getCelular().getStock() + ", Solicitados: " + item.getCantidad();
            }
        }

        try {
            // Calcular el total final aplicando las reglas internas (Suma + IVA 19%)

            venta.calcularTotal();

            //  Enviar a la base de datos de manera transaccional
            ventaDAO.guardarVentaCompleta(venta);

            return " Venta registrada con éxito. Factura N°: " + venta.getId() +
                    " | Total (con IVA 19%): $" + String.format("%.2f", venta.getTotal());

        } catch (SQLException e) {
            return " Error crítico al procesar la venta en la base de datos: " + e.getMessage();
        }
    }
}
