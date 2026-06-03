package main;

import view.MenuCelulares;
import view.MenuClientes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//import view.MenuCelulares;
//import view.MenuClientes;

                Scanner sc = new Scanner(System.in);
                int opcion = 0;

                do {
                    System.out.println("\n========================================");
                    System.out.println("     SISTEMA DE GESTIÓN TECNOSTORE      ");
                    System.out.println("========================================");
                    System.out.println("1. Gestión de Celulares (Inventario)");
                    System.out.println("2. Gestión de Clientes");
                    System.out.println("3. Registrar una Venta (Facturación)");
                    System.out.println("4. Reportes y Análisis (Stream API)");
                    System.out.println("5. Guardar y Exportar Reporte (.txt)");
                    System.out.println("6. Salir del Sistema");
                    System.out.println("========================================");
                    System.out.print("Seleccione una opción: ");

                    try {
                        opcion = Integer.parseInt(sc.nextLine());

                        switch (opcion) {
                            case 1:
                                MenuCelulares.mostrar(sc);
                                break;
                            case 2:
                                //MenuClientes.mostrar(sc);
                                break;
                            case 3:
                                System.out.println("\n[Próximamente: Submenú de Ventas]");
                                break;
                            case 4:
                                System.out.println("\n[Próximamente: Submenú de Reportes]");
                                break;
                            case 5:
                                System.out.println("\n[Próximamente: Exportar a TXT]");
                                break;
                            case 6:
                                System.out.println("\nCerrando sistema... ¡Gracias por usar TecnoStore!");
                                break;
                            default:
                                System.out.println("Opción no válida. Intente de nuevo.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Por favor, ingrese un número entero válido.");
                    }
                } while (opcion != 6);


                sc.close();
            }
        }

