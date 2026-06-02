package com.tecnostore.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

        // 1. Atributos de configuración de la base de datos
        private static final String URL = "jdbc:mysql://mysql-32224215-campoluis959-8cf6.b.aivencloud.com:15765/";
        private static final String USER = "avnadmin";
        private static final String PASSWORD = "AVNS_0A3HJtkFe_kRi9fz0Fe"; // Cambia esto por tu contraseña de MySQL

        // 2. La única instancia de la clase (estática y privada)
        private static ConexionDB instancia;

        // El objeto Connection de JDBC que se compartirá
        private Connection conexion;

        // 3. Constructor PRIVADO: Evita que se pueda usar "new ConexionDB()" desde fuera
        private ConexionDB() {
            try {
                // Registrar el driver de MySQL (Opcional en versiones modernas, pero buena práctica)
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Crear la conexión real
                this.conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión exitosa a la base de datos tecnostore_db.");
            } catch (ClassNotFoundException e) {
                System.err.println("❌ Error: No se encontró el Driver de MySQL. " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("❌ Error al conectar a la base de datos: " + e.getMessage());
            }
        }

        // 4.  público y estático para obtener la instancia única (Punto de acceso global)
        public static synchronized ConexionDB getInstancia() {
            // Si no se ha creado antes (es la primera vez que se llama), se crea.
            if (instancia == null) {
                instancia = new ConexionDB();
            } else {
                try {
                    // Si la conexión interna se cerró por alguna razón, la volvemos a abrir
                    if (instancia.getConexion().isClosed()) {
                        instancia = new ConexionDB();
                    }
                } catch (SQLException e) {
                    System.err.println("Error al verificar el estado de la conexión: " + e.getMessage());
                }
            }
            return instancia;
        }

        // 5.  obtener el objeto Connection y usarlo en los DAOs
        public Connection getConexion() {
            return conexion;
        }
}

