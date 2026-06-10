package main;

import view.MenuCelulares;
import view.MenuClientes;
import view.MenuVentas;
import utils.ArchivoUtils;

import java.util.Scanner;
import view.MenuReportes;

public class Main {
    public static void main(String[] args) {

//import view.MenuCelulares;
//import view.MenuClientes;

                Scanner sc = new Scanner(System.in);
                int opcion = 0;

                do {
                    System.out.println("\n========================================");
                    System.out.println("     SISTEMA DE GESTION TECNOSTORE      ");
                    System.out.println("========================================");
                    System.out.println("1. Gestion de Celulares (Inventario)");
                    System.out.println("2. Gestion de Clientes");
                    System.out.println("3. Registrar una Venta (Facturacion)");
                    System.out.println("4. Reportes y Analisis (Stream API)");
                    System.out.println("5. Guardar y Exportar Reporte (.txt)");
                    System.out.println("6. Salir del Sistema");
                    System.out.println("========================================");
                    System.out.print("Seleccione una opcion: ");

                    try {
                        opcion = Integer.parseInt(sc.nextLine());

                        switch (opcion) {
                            case 1:
                                MenuCelulares.mostrar(sc);
                                break;
                            case 2:
                                MenuClientes.mostrar(sc);
                                break;
                            case 3:
                                MenuVentas.mostrar(sc);
                                break;
                            case 4:
                                MenuReportes.mostrar(sc);
                                break;
                            case 5:
                               System.out.println("\n[ Guardar y Exportar Reporte (.txt) ]");


                                String textoAExportar = "====================================\n"
                                        + "      REPORTE DE INVENTARIO         \n"
                                        + "====================================\n"
                                        + "Total de celulares registrados: ... \n"
                                        + "Total de ventas: ... \n"
                                        + "Generado por el sistema JavaPro.\n";

                                // 2. Definimos el nombre del archivo
                                String nombreDelArchivo = "Reporte_Sistema.txt";

                                // 3. Llamamos a nuestra clase de utilidad
                                boolean exportadoConExito = ArchivoUtils.exportarReporteTxt(nombreDelArchivo, textoAExportar);

                                // 4. Le damos feedback al usuario
                                if (exportadoConExito) {
                                    System.out.println(" ¡Éxito! El reporte se ha guardado como: " + nombreDelArchivo);
                                    System.out.println("Lo puedes encontrar en la carpeta principal de tu proyecto.");
                                }
                                break;
                            case 6:
                                System.out.println("\nCerrando sistema... ¡Gracias por usar TecnoStore!");
                                break;
                            default:
                                System.out.println("Opcion no valida. Intente de nuevo.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Por favor, ingrese un numero entero valido.");
                    }
                } while (opcion != 6);


                sc.close();
            }
        }

