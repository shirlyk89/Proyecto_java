package dao;


import modelo.Celular;
import modelo.CategoriaGama;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CelularDAO {
    // Obtener la conexión única a través de nuestro Singleton
    private Connection getConexion() {
        return ConexionDB.getInstancia().getConexion();
    }

    //  Insertar un nuevo celular
    public void registrar(Celular celular) throws SQLException {
        String sql = "INSERT INTO celulares (marca, modelo, sistema_operativo, gama, precio, stock) VALUES (?, ?, ?, ?, ?, ?)";

        // El try-with-resources cierra automáticamente el PreparedStatement al terminar
        try (PreparedStatement ps = getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, celular.getMarca());
            ps.setString(2, celular.getModelo());
            ps.setString(3, celular.getSistemaOperativo());
            ps.setString(4, celular.getGama().name()); // Guardamos el Enum como String (ALTA, MEDIA, BAJA)
            ps.setDouble(5, celular.getPrecio());
            ps.setInt(6, celular.getStock());

            ps.executeUpdate();

            // Obtener el ID autogenerado por MySQL y asignárselo al objeto
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    celular.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // LEER: Listar todos los celulares
    public List<Celular> listarTodos() throws SQLException {
        List<Celular> lista = new ArrayList<>();
        String sql = "SELECT * FROM celulares";

        try (PreparedStatement ps = getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Celular c = new Celular(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("sistema_operativo"),
                        CategoriaGama.valueOf(rs.getString("gama")), // Convierte String de nuevo a Enum
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
                lista.add(c);
            }
        }
        return lista;
    }

    // ACTUALIZAR: Modificar datos de un celular existente
    public void actualizar(Celular celular) throws SQLException {
        String sql = "UPDATE celulares SET marca=?, modelo=?, sistema_operativo=?, gama=?, precio=?, stock=? WHERE id=?";

        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, celular.getMarca());
            ps.setString(2, celular.getModelo());
            ps.setString(3, celular.getSistemaOperativo());
            ps.setString(4, celular.getGama().name());
            ps.setDouble(5, celular.getPrecio());
            ps.setInt(6, celular.getStock());
            ps.setInt(7, celular.getId());

            ps.executeUpdate();
        }
    }

    //  ELIMINAR: Borrar un celular por su ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM celulares WHERE id=?";

        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Buscar un celular específico por ID (Muy útil para el flujo de ventas más adelante)
    public Celular buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM celulares WHERE id=?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Celular(
                            rs.getInt("id"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getString("sistema_operativo"),
                            CategoriaGama.valueOf(rs.getString("gama")),
                            rs.getDouble("precio"),
                            rs.getInt("stock")
                    );
                }
            }
        }
        return null;
    }
}
