/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author SHIRLY LEAL
 */
    

import controller.GestorCelulares;
import model.CategoriaGama;
import model.Celular;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import utils.ArchivoUtils;

public class MenuReportes {

    public static void mostrar(Scanner sc) {
        GestorCelulares gestor = new GestorCelulares();
        int opcion = 0;

        do {
            ArchivoUtils.limpiarConsola();
            System.out.println("\n=========================================");
            System.out.println("     MODULO DE REPORTES Y ANALISIS    ");
            System.out.println("=========================================");
            System.out.println("1. Alerta de Bajo Stock (5 unidades o menos)");
            System.out.println("2. Valor Total Financiero del Inventario");
            System.out.println("3. Celular Mas Caro y Mas Barato");
            System.out.println("4. Cantidad de Celulares por Gama");
            System.out.println("5. Volver al Menu Principal");
            System.out.print("Seleccione un reporte: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                
              
                List<Celular> celulares = gestor.obtenerTodosLosCelulares();

                if (celulares.isEmpty() && opcion != 5) {
                    System.out.println(" No hay datos en el sistema para generar reportes.");
                    continue;
                }

                switch (opcion) {
                    case 1:
                        ArchivoUtils.limpiarConsola();
                        System.out.println("\n [ REPORTES: ALERTA DE BAJO STOCK ] ");
                       
                        List<Celular> bajoStock = celulares.stream()
                                .filter(c -> c.getStock() <= 5)
                                .toList();

                        if (bajoStock.isEmpty()) {
                            System.out.println("Excelente! Todos los productos tienen buen stock.");
                        } else {
                            System.out.printf("%-5s | %-12s | %-15s | %-7s\n", "ID", "MARCA", "MODELO", "STOCK");
                            System.out.println("---------------------------------------------------");
                            bajoStock.forEach(c -> System.out.printf("%-5d | %-12s | %-15s | %-7d\n",
                                    c.getId(), c.getMarca(), c.getModelo(), c.getStock()));
                        }
                        break;

                    case 2:
                        ArchivoUtils.limpiarConsola();
                        System.out.println("\n [ REPORTE: VALOR FINANCIERO DEL INVENTARIO ]");
                       
                        double valorTotal = celulares.stream()
                                .mapToDouble(c -> c.getPrecio() * c.getStock())
                                .sum();
                        
                       
                        int unidadesTotales = celulares.stream()
                                .mapToInt(Celular::getStock)
                                .sum();

                        System.out.printf(" Cantidad total de celulares en almacen: %d unidades.\n", unidadesTotales);
                        System.out.printf(" Capital total invertido en inventario : $%,.2f\n", valorTotal);
                        break;

                    case 3:
                        ArchivoUtils.limpiarConsola();
                        System.out.println("\n [ REPORTE: EXTREMOS DE PRECIOS ]");
                       
                        Optional<Celular> masCaro = celulares.stream()
                                .max(Comparator.comparingDouble(Celular::getPrecio));

                       
                        Optional<Celular> masBarato = celulares.stream()
                                .min(Comparator.comparingDouble(Celular::getPrecio));

                        masCaro.ifPresent(c -> System.out.printf(" MAS CARO: %s %s ($%,.2f)\n", 
                                c.getMarca(), c.getModelo(), c.getPrecio()));
                        
                        masBarato.ifPresent(c -> System.out.printf(" MAS BARATO: %s %s ($%,.2f)\n", 
                                c.getMarca(), c.getModelo(), c.getPrecio()));
                        break;

                    case 4:
                        ArchivoUtils.limpiarConsola();
                        System.out.println("\n [ REPORTE: DISTRIBUCION POR GAMA ]");
                        
                        Map<CategoriaGama, Long> conteoPorGama = celulares.stream()
                                .collect(Collectors.groupingBy(c -> c.getGama(), Collectors.counting()));

                        conteoPorGama.forEach((gama, cantidad) -> 
                                System.out.printf(" Gama %-6s: %d modelos registrados.\n", gama, cantidad));
                        break;

                    case 5:
                        ArchivoUtils.limpiarConsola();
                        System.out.println("Regresando al Menu Principal...");
                        break;

                    default:
                        System.out.println(" Opcion invalida. Intente del 1 al 5.");
                }

            } catch (NumberFormatException e) {
                System.out.println(" ERROR: Ingrese un numero entero valido.");
            } catch (SQLException e) {
                System.out.println(" Error al conectar a la BD para generar el reporte: " + e.getMessage());
            }

        } while (opcion != 5);
    }
}
    

