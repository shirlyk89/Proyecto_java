package Tes;

import Tes.ReporteDAO;
import Tes.CelularTes;
import Tes.ClienteTes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ReporteController {

    private ReporteDAO reporteDAO;

    public ReporteController() {
        this.reporteDAO = new ReporteDAO();
    }

    // Método principal que agrupa todo y genera el TXT
    public void generarReporteTxt(String rutaArchivo) {
        // Obtenemos los datos del DAO
        double totalVentas = reporteDAO.obtenerTotalVentas();
        Map<String, Integer> ventasPorModelo = reporteDAO.obtenerVentasPorModelo();
        List<ClienteTes> clientesCredito = reporteDAO.obtenerClientesConCredito();
        List<CelularTes> alertasStock = reporteDAO.obtenerCelularesStockBajo();

        // Al ejecutar esto desde tu entorno en IntelliJ, una ruta como
        // "reporte_tecnostore.txt"
        // creará el archivo directamente en la raíz de tu proyecto.
        try (PrintWriter out = new PrintWriter(new FileWriter(rutaArchivo))) {

            out.println("=========================================");
            out.println("       REPORTE CONSOLIDADO TECNOSTORE    ");
            out.println("=========================================");
            out.println();

            // 1 y 2. Total de ventas y validación (Regla de negocio: validar si no hay
            // ventas)
            out.println("--- 1. TOTAL DE VENTAS ---");
            if (totalVentas <= 0 || ventasPorModelo.isEmpty()) {
                out.println("Aviso: No se registran ventas en el sistema actualmente.");
            } else {
                out.println("Monto total facturado: $" + String.format("%.2f", totalVentas));
                out.println();

                out.println("--- 2. VENTAS POR MODELO ---");
                for (Map.Entry<String, Integer> entry : ventasPorModelo.entrySet()) {
                    out.println("Modelo: " + entry.getKey() + " | Cantidad vendida: " + entry.getValue());
                }
            }
            out.println();

            // 3. Listar clientes con crédito pendiente
            out.println("--- 3. CLIENTES CON CRÉDITO PENDIENTE ---");
            if (clientesCredito.isEmpty()) {
                out.println("No hay clientes con saldo pendiente.");
            } else {
                for (ClienteTes c : clientesCredito) {
                    out.println("NIT: " + c.getIdentificacion() + " | Nombre: " + c.getNombre() + " | Saldo: $"
                            + String.format("%.2f", c.getSaldoPendiente()));
                }
            }
            out.println();

            // 4. Mostrar alertas cuando el stock esté al mínimo
            out.println("--- 4. ALERTAS DE STOCK MÍNIMO ---");
            if (alertasStock.isEmpty()) {
                out.println("Todo el inventario está en niveles óptimos.");
            } else {
                for (CelularTes cel : alertasStock) {
                    out.println("¡ALERTA! Modelo: " + cel.getModelo() + " | Stock actual: " + cel.getStock()
                            + " (Mínimo requerido: " + cel.getStockMinimo() + ")");
                }
            }

            System.out.println("Éxito: El reporte ha sido generado correctamente en la ruta: " + rutaArchivo);

        } catch (IOException e) {
            System.err.println("Error de E/S al generar el archivo TXT: " + e.getMessage());
        }
    }

}
