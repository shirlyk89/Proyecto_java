package view;

import utils.Validador;
import controller.GestorClientes;
import java.sql.SQLException;
import model.Cliente;

import java.util.List;

import java.util.Scanner;

public class MenuClientes {
    public static void mostrar(Scanner sc){
        int opcion = 0;
        do {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion){
                    case 1:
                        System.out.println("\n[ Registrar Nuevo Cliente ]");
                        System.out.print("Nombre completo: ");
                        String nombre = sc.nextLine();

                        // --- VALIDACIÓN DE IDENTIFICACIÓN ---
                        String identificacion;
                        do {
                            System.out.print("Número de Identificación (Cédula): ");
                            identificacion = sc.nextLine();
                            if (!Validador.validarNumero(identificacion)) {
                                System.out.println("ERROR: La identificación debe contener solo números.");
                            }
                }while(!Validador.validarNumero(identificacion));
                        //---VALIDAR CORREO---
                        String correo;
                        do {
                            System.out.println("Correo electronico: ");
                            correo = sc.nextLine();
                            if (!Validador.validarCorreo(correo)){
                                System.out.println("Formato de correo invalido. Ingresalo nuevamente");
                            }
                        }while (!Validador.validarCorreo(correo));

                        // --VALIDAR NUMERO TELEFONICO--
                        String telefono;
                        do {
                            System.out.println("Telefono: ");
                            telefono = sc.nextLine();
                            if (!Validador.validarNumero(telefono)){
                                System.out.println("ERROR. El numero telefonico debe contener solo numeros");
                            }
                        }while (!Validador.validarNumero(telefono));
                        System.out.println("\n ¡Datos del cliente capturados correctamente!");
                        
                          //Crear el objeto y llena los datos ingresados
                        Cliente nuevoCliente = new Cliente();
                        nuevoCliente.setNombre(nombre);
                        nuevoCliente.setIdentificacion(identificacion);
                        nuevoCliente.setCorreo(correo);
                        nuevoCliente.setTelefono(telefono);

                        // Llama al método del gestor 
                        GestorClientes gestor = new GestorClientes();
                        String resultado = gestor.registrarCliente(nuevoCliente);

                        //  Imprimir la respuesta de la BD
                        System.out.println(resultado); 
                        break;
                    case 2:
                      System.out.println("\n--- LISTADO DE CLIENTES REGISTRADOS ---");
                        GestorClientes gestorClientes = new GestorClientes();
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
                            System.out.println(" Error al conectar con la base de datos: " + e.getMessage());
                        }
                        break;


                    case 3:
                        System.out.println("Regresando al menú principal...");
                        break;

                    default:
                        System.out.println(" Opción no válida (1-3).");
                        break;
                }
            }catch (NumberFormatException e){
                System.out.println("Opcion invalida. Ingrese un numero entero.");
            }
        }while(opcion != 3);

    }
}
