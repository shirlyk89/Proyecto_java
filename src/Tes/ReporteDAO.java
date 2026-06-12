package Tes;

import Tes.CelularTes;
import Tes.ClienteTes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.CategoriaGama;
import persistencia.ConexionDB;

public class ReporteDAO {

    private Connection obtenerConexion() throws SQLException {
        // Retorna la instancia de tu conexión a MySQL
        return ConexionDB.getConnection();
    }

    // 1. Registrar el total de ventas (suma de montos)
    public double obtenerTotalVentas() {
        double total = 0.0;
        String sql = "SELECT SUM(total_facturado) AS total FROM ventas";

        try (Connection con = obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión al obtener total de ventas: " + e.getMessage());
        }
        return total;
    }

    // 2. Agrupar por modelo la cantidad total de celulares vendidos
    public Map<String, Integer> obtenerVentasPorModelo() {
        Map<String, Integer> reporte = new HashMap<>();
        String sql = "SELECT c.modelo, SUM(d.cantidad) AS total_vendidos " +
                "FROM detalle_ventas d " +
                "INNER JOIN celulares c ON d.id_celular = c.id_celular " +
                "GROUP BY c.modelo";

        try (Connection con = obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                reporte.put(rs.getString("modelo"), rs.getInt("total_vendidos"));
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión al agrupar ventas por modelo: " + e.getMessage());
        }
        return reporte;
    }

    // 3. Listar clientes con crédito pendiente
    public List<ClienteTes> obtenerClientesConCredito() {
        List<ClienteTes> clientes = new ArrayList<>();
        String sql = "SELECT id_cliente, nit, nombre, saldo_pendiente FROM clientes WHERE saldo_pendiente > 0";

        try (Connection con = obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ClienteTes c = new ClienteTes(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        rs.getDouble("saldo_pendiente"));
                clientes.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión al obtener clientes con crédito: " + e.getMessage());
        }
        return clientes;
    }

    // 4. Mostrar alertas cuando el stock esté al mínimo
    public List<CelularTes> obtenerCelularesStockBajo() {
        List<CelularTes> celulares = new ArrayList<>();
        String sql = "SELECT id_celular, modelo, stock, stock_minimo FROM celulares WHERE stock <= stock_minimo";

        try (Connection con = obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CelularTes cel = new CelularTes(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("sistema_operativo"),
                        CategoriaGama.valueOf(rs.getString("gama")),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getInt("stock_minimo"));
                celulares.add(cel);
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión al obtener alertas de stock: " + e.getMessage());
        }
        return celulares;
    }
}