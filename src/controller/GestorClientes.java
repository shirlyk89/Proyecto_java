package controller;

import model.Cliente;
import persistencia.ClienteDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class GestorClientes {
      private ClienteDAO clienteDAO;

    // Patrón Regex estándar para validar la estructura de un correo electrónico
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public void ClienteControlador() {
        this.clienteDAO = new ClienteDAO();
    }

    // Registrar cliente con validaciones complejas
    public String registrarCliente(Cliente cliente) {
        // Validar el formato del correo electrónico usando el patrón Regex
        if (cliente.getCorreo() == null || !Pattern.matches(EMAIL_REGEX, cliente.getCorreo())) {
            return " Error: El formato del correo electrónico no es válido.";
        }

        try {
            //  Validar que el número de identificación sea único
            Cliente clienteExistente = clienteDAO.buscarPorIdentificacion(cliente.getIdentificacion());
            if (clienteExistente != null) {
                return " Error: Ya existe un cliente registrado con la identificación: " + cliente.getIdentificacion();
            }

            // Si pasa todas las validaciones, se guarda en la base de datos
            clienteDAO.registrar(cliente);
            return " Cliente registrado con éxito. ID asignado: " + cliente.getId();

        } catch (SQLException e) {
            return " Error en la base de datos al registrar cliente: " + e.getMessage();
        }
    }

    public List<Cliente> obtenerTodosLosClientes() throws SQLException {
        return clienteDAO.listarTodos();
    }
}
