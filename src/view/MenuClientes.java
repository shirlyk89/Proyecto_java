package view;

import utils.Validador;

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
                        System.out.println("Próximamente: Verificando unicidad en DB y guardando a través de ClienteDAO...");
                        break;
                    case 2:
                        System.out.println("\n[ Listando todos los clientes registrados... ]");
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
