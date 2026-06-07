package persistencia;

import model.Venta;
import model.ItemVenta;
import java.sql.*;

public class VentaDAO {
    private Connection getConexion() {
        return ConexionDB.getInstancia().getConexion();
    }

    // Guardar la venta y sus detalles usando una Transacción SQL
    public void guardarVentaCompleta(Venta venta) throws SQLException {
        Connection conn = getConexion();

        String sqlVenta = "INSERT INTO ventas (id_cliente, fecha, total) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        String sqlActualizarStock = "UPDATE celulares SET stock = stock - ? WHERE id = ?";

        // Desactivamos el auto-commit para manejar la transacción manualmente
        conn.setAutoCommit(false);

        try (PreparedStatement psVenta = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);
             PreparedStatement psStock = conn.prepareStatement(sqlActualizarStock)) {

            //  Insertar en la tabla 'ventas' (Encabezado de la factura)
            psVenta.setInt(1, venta.getCliente().getId());
            psVenta.setTimestamp(2, new java.sql.Timestamp(venta.getFecha().getTime()));
            psVenta.setDouble(3, venta.getTotal());
            psVenta.executeUpdate();

            // Obtener el ID de la venta generado por MySQL
            int idVentaGenerado = -1;
            try (ResultSet rs = psVenta.getGeneratedKeys()) {
                if (rs.next()) {
                    idVentaGenerado = rs.getInt(1);
                    venta.setId(idVentaGenerado); // Se lo asignamos al objeto Java
                }
            }

            //  Insertar cada producto en 'detalle_ventas' y actualizar su stock
            for (ItemVenta item : venta.getItems()) {
                // Insertar detalle
                psDetalle.setInt(1, idVentaGenerado);
                psDetalle.setInt(2, item.getCelular().getId());
                psDetalle.setInt(3, item.getCantidad());
                psDetalle.setDouble(4, item.getSubtotal());
                psDetalle.executeUpdate();

                // Actualizar inventario del celular
                psStock.setInt(1, item.getCantidad());
                psStock.setInt(2, item.getCelular().getId());
                psStock.executeUpdate();
            }

            //  confirmamos los cambios en MySQL
            conn.commit();
            System.out.println("💾 Transacción completada en la base de datos.");

        } catch (SQLException e) {
            // Si ocurrió CUALQUIER error, deshacemos  lo que se alcanzó a hacer
            conn.rollback();
            throw e; 
        } finally {
           
            conn.setAutoCommit(true);
        }
    }
}
