package view;

import controller.GestorCelulares;
import model.Celular;
import utils.Validador;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuCelulares {

    public static void mostrar(Scanner sc){
        int opcion = 0;
        do {
            System.out.println("\n--- SUBMENÚ CELULARES ---");
            System.out.println("1. Registrar Celular");
            System.out.println("2. Listar Catalogo");
            System.out.println("3. Actualizar Celular");
            System.out.println("4. Eliminar Celular");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            try{
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion){
                    case 1:
                    System.out.println("Registrando...");
                    System.out.print("Marca: "); String marca = sc.nextLine();
                    System.out.print("Modelo: "); String modelo = sc.nextLine();
                        double precio = 0;
                        boolean precioValido = false;
                    do {
                        // PARA VALIDAR QUE EL PRECIO SEA VALIDO
                        try {
                            System.out.println("Precio: ");
                            precio = Double.parseDouble(sc.nextLine());

                            if (Validador.validarPrecio(precio)){
                                precioValido = true;
                            }else {
                                System.out.println("ERROR. El precio debe ser un numero mayor a 0");
                            }
                        }catch (NumberFormatException e){
                            System.out.println("ERROR. Debe ingresar un valor numerico valido. No use letras");
                        }
                    }while (!precioValido);
                        int stock = 0;
                        boolean stockValido = false;
                        do {
                            // VALIDAR QUE EL STOCK NO SEA 0
                            try{
                                System.out.println("Stock inicial: ");
                                stock = Integer.parseInt(sc.nextLine());
                                if (Validador.validarStock(stock)){
                                    stockValido = true;
                                }else{
                                    System.out.println("ERROR. El stock debe ser mayor a 0");
                                }
                            }catch (NumberFormatException e){
                                System.out.println("ERROR. Debe ingresar un numero entero valido");
                            }
                        }while (!stockValido);

                        // COMPLETAR OTRAS ESPECIFICACIONES

                        System.out.println("Sistema Operativo");
                        String so = sc.nextLine();
                        System.out.println("GAMA: ('Alta', 'Media', 'Baja'): ");
                        String gama = sc.nextLine().toUpperCase();
                        System.out.println("\n ¡Datos capturados correctamente!");
                        System.out.println("Proximamente: Enviando a FactoryCelular y guardando en DB...");
                        break;


                    case 2:
                        System.out.println("\n================================================================================");
                        System.out.println("                         CATÁLOGO DE CELULARES REGISTRADOS                      ");
                        System.out.println("================================================================================");

                        try {
                            GestorCelulares gestor = new GestorCelulares();

                            List<Celular> listaCelulares = gestor.obtenerTodosLosCelulares();

                            if (listaCelulares.isEmpty()) {
                                System.out.println(" No hay ningún celular registrado en el sistema actualmente.");
                            } else {

                                System.out.printf("%-5s | %-12s | %-15s | %-12s | %-7s | %-10s\n",
                                        "ID", "MARCA", "MODELO", "PRECIO", "STOCK", "GAMA");
                                System.out.println("--------------------------------------------------------------------------------");

                                for (Celular cel : listaCelulares) {
                                    System.out.printf("%-5d | %-12s | %-15s | $%-11.2f | %-7d | %-10s\n",
                                            cel.getId(),
                                            cel.getMarca(),
                                            cel.getModelo(),
                                            cel.getPrecio(),
                                            cel.getStock(),
                                            cel.getGama().toString()
                                    );
                                }
                            }
                        } catch (SQLException e) {
                            System.out.println("Error al intentar conectar con la Base de Datos: " + e.getMessage());
                        }
                        System.out.println("================================================================================");
                        break;


                    case 3:
                        System.out.println("\n[ Modificando celular... ]");
                        break;

                    case 4:
                        System.out.println("\n[ Eliminando celular... ]");
                        break;

                    case 5:
                        System.out.println("Regresando al menú principal...");
                        break;

                } // AQUÍ CIERRA EL SWITCH

            }catch (NumberFormatException e){
                System.out.println("Opcion invalida (1-5).");
            }


        }

        while (opcion != 5);

    }
}
