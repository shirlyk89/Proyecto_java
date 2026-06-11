package view;

import utils.Validador;

import java.util.Scanner;
import controller.GestorClientes;
import model.Cliente;
import java.util.List;

import java.sql.SQLException;
import utils.ArchivoUtils;

public class MenuClientes {
    public static void mostrar(Scanner sc){
        int opcion = 0;
        do {
            ArchivoUtils.limpiarConsola();
            System.out.println("\n--- GESTION DE CLIENTES ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Volver al Menu Principal");
            System.out.print("Seleccione una opcion: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion){
                    case 1:
                        ArchivoUtils.limpiarConsola();
                        System.out.println("\n[ Registrar Nuevo Cliente ]");
                        System.out.print("Nombre completo: ");
                        String nombre = sc.nextLine();

                        // --- VALIDACIÓN DE IDENTIFICACIÓN ---
                        String identificacion;
                        do {
                            System.out.print("Numero de Identificacion (Cedula): ");
                            identificacion = sc.nextLine();
                            if (!Validador.validarNumero(identificacion)) {
                                System.out.println("ERROR: La identificacion debe contener solo numeros.");
                            }
                        } while(!Validador.validarNumero(identificacion));

                        //---VALIDAR CORREO---
                        String correo;
                        do {
                            System.out.println("Correo electronico: ");
                            correo = sc.nextLine();
                            if (!Validador.validarCorreo(correo)){
                                System.out.println("Formato de correo invalido. Ingresalo nuevamente");
                            }
                        } while (!Validador.validarCorreo(correo));

                        // --VALIDAR NUMERO TELEFONICO--
                        String telefono;
                        do {
                            System.out.println("Telefono: ");
                            telefono = sc.nextLine();
                            if (!Validador.validarNumero(telefono)){
                                System.out.println("ERROR. El numero telefonico debe contener solo numeros");
                            }
                        } while (!Validador.validarNumero(telefono));

                        // crea el objeto vacío y lo llena con los datos ingresados
                        Cliente nuevoCliente = new Cliente();
                        nuevoCliente.setNombre(nombre);
                        nuevoCliente.setIdentificacion(identificacion);
                        nuevoCliente.setCorreo(correo);
                        nuevoCliente.setTelefono(telefono);

                        // Llamar al método del gestor 
                        GestorClientes gestor = new GestorClientes();
                        System.out.println("\n ¡Datos capturados! Guardando en base de datos...");
                        String resultado = gestor.registrarCliente(nuevoCliente);
                        System.out.println(resultado); 
                       
                        break;
                        
                    case 2:
                        ArchivoUtils.limpiarConsola();
                        System.out.println("\n--- LISTADO DE CLIENTES REGISTRADOS ---");
                        GestorClientes gestorClientes = new GestorClientes();
                        
                        // Envolvemos el listado en try-catch porque obtenerTodosLosClientes() SI lanza SQLException
                        try {
                            List<Cliente> listaClientes = gestorClientes.obtenerTodosLosClientes();

                            if (listaClientes.isEmpty()) {
                                System.out.println("No hay clientes registrados en la base de datos.");
                            } else {
                                System.out.printf("| %-4s | %-30s | %-15s | %-30s | %-15s |%n", "ID", "NOMBRE", "IDENTIFICACIÓN", "CORREO", "TELÉFONO");
                                System.out.println("----------------------------------------------------------------------------------");
                                for (Cliente c : listaClientes) {
                                    System.out.printf("| %-4d | %-30.30s | %-15.15s | %-30.30s | %-15.15s |%n",
                                            c.getId(),
                                            c.getNombre(),
                                            c.getIdentificacion(),
                                            c.getCorreo(),
                                            c.getTelefono());
                                }
                            }
                        } catch (SQLException e) {
                            System.out.println(" Error al conectar con la base de datos al listar: " + e.getMessage());
                        }
                        break;


                    case 3:
                        ArchivoUtils.limpiarConsola();
                        System.out.println("Regresando al menu principal...");
                        break;

                    default:
                        System.out.println(" Opciun no valida (1-3).");
                        break;
                }
            }catch (NumberFormatException e){
                System.out.println("Opcion invalida. Ingrese un numero entero.");
            }
        }while(opcion != 3);

    }
}

