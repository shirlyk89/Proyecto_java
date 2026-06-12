package main;

import Tes.ReporteController;
import controller.GestorCelulares;
import controller.GestorVentas;
import view.MenuCelulares;
import view.MenuClientes;
import view.MenuVentas;
import java.sql.SQLException;

import java.util.Scanner;
import view.MenuReportes;
import utils.ArchivoUtils;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            ArchivoUtils.limpiarConsola();
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
                        System.out.println("\n[ Guardar y Exportar Reporte Completo (.txt) ]");

                        // 2. Instanciamos el controlador del reporte consolidado
                        ReporteController reporteCtrl = new ReporteController();

                        // 3. Ejecutamos el método que realiza todo el proceso de negocio y exportación
                        // El archivo se guardará en la raíz del proyecto.
                        reporteCtrl.generarReporteTxt("Reporte_Sistema.txt");

                        // Pausa para que el usuario pueda leer el mensaje de éxito o error en consola
                        System.out.println("\nPresione Enter para continuar...");
                        sc.nextLine();

                        ArchivoUtils.limpiarConsola();
                        break;

                    case 6:
                        System.out.println("\nCerrando sistema... ¡Gracias por usar TecnoStore!");
                        ArchivoUtils.limpiarConsola();
                        break;
                    default:
                        System.out.println("Opcion no valida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un numero entero valido.");
                System.out.println("\nPresione Enter para continuar...");
                sc.nextLine();
            }
        } while (opcion != 6);

        sc.close();
    }
}
