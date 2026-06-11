package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Celular;
import persistencia.CelularDAO;
import java.sql.SQLException;
import java.util.List;
import persistencia.ConexionDB;

public class GestorCelulares {
   private CelularDAO celularDAO;

    public GestorCelulares() {
        this.celularDAO = new CelularDAO();
    }

    // Lógica para registrar con validaciones
    public String registrarCelular(Celular celular) {
        // Validación obligatoria: Precio y stock deben ser positivos
        if (celular.getPrecio() <= 0) {
            return " Error: El precio del celular debe ser mayor a cero.";
        }
        if (celular.getStock() < 0) {
            return " Error: El stock no puede ser un número negativo.";
        }

        try {
            celularDAO.registrar(celular);
            return " Celular registrado exitosamente con ID: " + celular.getId();
        } catch (SQLException e) {
            return " Error al registrar en la base de datos: " + e.getMessage();
        }
    }

    public List<Celular> obtenerTodosLosCelulares() throws SQLException {
        return celularDAO.listarTodos();
    }

    // Lógica para actualizar con validaciones
    public String actualizarCelular(Celular celular) {
        if (celular.getPrecio() <= 0) {
            return " Error: El precio debe ser mayor a cero.";
        }
        if (celular.getStock() < 0) {
            return " Error: El stock no puede ser negativo.";
        }

        try {
            // Verificamos primero si existe
            if (celularDAO.buscarPorId(celular.getId()) == null) {
                return " Error: El celular con ID " + celular.getId() + " no existe.";
            }
            celularDAO.actualizar(celular);
            return " Celular actualizado correctamente.";
        } catch (SQLException e) {
            return " Error al actualizar en la base de datos: " + e.getMessage();
        }
    }

    public String eliminarCelular(int id) {
        try {
            if (celularDAO.buscarPorId(id) == null) {
                return " Error: No se encontró ningún celular con el ID especificado.";
            }
            celularDAO.eliminar(id);
            return " Celular eliminado correctamente del inventario.";
        } catch (SQLException e) {
            return " Error al eliminar (es posible que esté asociado a una venta): " + e.getMessage();
        }
    }
    
    public Celular buscarCelularPorId(int id) {
        try {
            return celularDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.out.println(" Error en la base de datos al buscar celular: " + e.getMessage());
            return null;
        }
    }
    
    public void actualizarStock(int idCelular, int nuevoStock) {
    String sql = "UPDATE celulares SET stock = ? WHERE id = ?";
    
    // 1. Llamamos a tu Singleton para obtener la conexión única y global
    Connection con = ConexionDB.getInstancia().getConexion();
    
    // 2. IMPORTANTE: Solo colocamos el PreparedStatement dentro del try() 
    // No metas la "Connection con" ahí, porque el try-with-resources la cerraría automáticamente 
    // al terminar el método, destruyendo tu Singleton para el resto de la aplicación.
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setInt(1, nuevoStock);
        ps.setInt(2, idCelular);
        ps.executeUpdate();
        
    } catch (SQLException e) {
        System.out.println("ERROR al actualizar el stock en la base de datos: " + e.getMessage());
    }
}
    
}

