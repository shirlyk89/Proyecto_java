package view;

import controller.GestorCelulares;
import model.CategoriaGama;
import model.Celular;
import utils.Validador;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import utils.ArchivoUtils;

public class MenuCelulares {

    public static void mostrar(Scanner sc){
        int opcion = 0;
        do {
           
            ArchivoUtils.limpiarConsola();
            System.out.println("\n--- SUBMENU CELULARES ---");
            System.out.println("1. Registrar Celular");
            System.out.println("2. Listar Catalogo");
            System.out.println("3. Actualizar Celular");
            System.out.println("4. Eliminar Celular");
            System.out.println("5. Volver al Menu Principal");
            System.out.print("Seleccione una opcion: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion){
                    case 1:
                        ArchivoUtils.limpiarConsola();
                        
                        System.out.println("\n--- REGISTRAR CELULAR ---");
                        System.out.print("Marca: "); String marca = sc.nextLine();
                        System.out.print("Modelo: "); String modelo = sc.nextLine();
                        
                        double precio = 0;
                        boolean precioValido = false;
                        do {
                            try {
                                System.out.print("Precio: ");
                                precio = Double.parseDouble(sc.nextLine());

                                if (Validador.validarPrecio(precio)){
                                    precioValido = true;
                                } else {
                                    System.out.println("ERROR. El precio debe ser un numero mayor a 0");
                                }
                            } catch (NumberFormatException e){
                                System.out.println("ERROR. Debe ingresar un valor numerico valido. No use letras");
                            }
                        } while (!precioValido);

                        int stock = 0;
                        boolean stockValido = false;
                        do {
                            try {
                                System.out.print("Stock inicial: ");
                                stock = Integer.parseInt(sc.nextLine());
                                if (Validador.validarStock(stock)){
                                    stockValido = true;
                                } else {
                                    System.out.println("ERROR. El stock debe ser mayor a 0");
                                }
                            } catch (NumberFormatException e){
                                System.out.println("ERROR. Debe ingresar un numero entero valido");
                            }
                        } while (!stockValido);

                        System.out.print("Sistema Operativo: ");
                        String so = sc.nextLine();
                        System.out.print("GAMA: ('Alta', 'Media', 'Baja'): ");
                        String gama = sc.nextLine().toUpperCase();
                        CategoriaGama gamaEnum = CategoriaGama.valueOf(gama);
                        
                        System.out.println("\n✨ ¡Datos capturados correctamente!");
                        Celular nuevoCelular = new Celular(0, marca, modelo, so, gamaEnum, precio, stock);

                        GestorCelulares gestor = new GestorCelulares();
                        String respuesta = gestor.registrarCelular(nuevoCelular);
                        System.out.println("\n" + respuesta);
                        break;

                    case 2:
                        ArchivoUtils.limpiarConsola();
                        System.out.println("\n================================================================================");
                        System.out.println("                          CATALOGO DE CELULARES REGISTRADOS                      ");
                        System.out.println("================================================================================");

                        try {
                            gestor = new GestorCelulares();
                            List<Celular> listaCelulares = gestor.obtenerTodosLosCelulares();

                            if (listaCelulares.isEmpty()) {
                                System.out.println(" No hay ningun celular registrado en el sistema actualmente.");
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

                    case 3: { 
                        ArchivoUtils.limpiarConsola();
                        System.out.println("\n--- ACTUALIZAR CELULAR EXISTENTE ---");
                        
                        System.out.print("Ingrese el ID del celular a modificar: ");
                        int idActualizar = Integer.parseInt(sc.nextLine());

                        System.out.print("Nueva Marca: "); 
                        String nuevaMarca = sc.nextLine();
                        System.out.print("Nuevo Modelo: "); 
                        String nuevoModelo = sc.nextLine();
                        
                        // VALIDACIÓN DEL NUEVO PRECIO
                        double nuevoPrecio = 0;
                        boolean nuevoPrecioValido = false;
                        do {
                            try {
                                System.out.print("Nuevo Precio: ");
                                nuevoPrecio = Double.parseDouble(sc.nextLine());
                                if (Validador.validarPrecio(nuevoPrecio)){
                                    nuevoPrecioValido = true;
                                } else {
                                    System.out.println("ERROR. El precio debe ser un numero mayor a 0");
                                }
                            } catch (NumberFormatException e){
                                System.out.println("ERROR. Debe ingresar un valor numerico valido.");
                            }
                        } while (!nuevoPrecioValido);

                        // VALIDACIÓN DEL NUEVO STOCK 
                        int nuevoStock = 0;
                        boolean nuevoStockValido = false;
                        do {
                            try {
                                System.out.print("Nuevo Stock: ");
                                nuevoStock = Integer.parseInt(sc.nextLine());
                                if (Validador.validarStock(nuevoStock)){
                                    nuevoStockValido = true;
                                } else {
                                    System.out.println(" ERROR. El stock debe ser mayor a 0");
                                }
                            } catch (NumberFormatException e){
                                System.out.println(" ERROR. Debe ingresar un numero entero valido");
                            }
                        } while (!nuevoStockValido);

                        System.out.print("Nuevo Sistema Operativo: ");
                        String nuevoSo = sc.nextLine();
                        
                        System.out.print("Nueva GAMA ('Alta', 'Media', 'Baja'): ");
                        String nuevaGama = sc.nextLine().toUpperCase();
                        CategoriaGama nuevaGamaEnum = CategoriaGama.valueOf(nuevaGama);

                        System.out.println("\n⏳ Enviando actualización...");
                        
                        Celular celularModificado = new Celular(idActualizar, nuevaMarca, nuevoModelo, nuevoSo, nuevaGamaEnum, nuevoPrecio, nuevoStock);
                        GestorCelulares gestorActualizacion = new GestorCelulares();
                        
                        
                        String respuestaAct = gestorActualizacion.actualizarCelular(celularModificado);
                        
                        System.out.println(respuestaAct);
                        break;
                    }

                    case 4:{
                        ArchivoUtils.limpiarConsola();
                        System.out.println("\n--- ELIMINAR CELULAR POR ID ---");

                        int idEliminar = 0;
                        boolean idValido = false;

                        // Bucle para asegurar que ingresen un número válido
                        do {
                            try {
                                System.out.print("Ingrese el ID del celular que desea eliminar: ");
                                idEliminar = Integer.parseInt(sc.nextLine());
                                idValido = true;
                            } catch (NumberFormatException e) {
                                System.out.println(" ERROR: El ID debe ser un número entero valido. Intente de nuevo.");
                            }
                        } while (!idValido);

                        System.out.print(" ¿Esta seguro que desea eliminar el celular con ID " + idEliminar + "? (S/N): ");
                        String confirmacion = sc.nextLine().trim().toUpperCase();

                        if (confirmacion.equals("S")) {
                            System.out.println("\n⏳ Procesando eliminación en la Base de Datos...");

                            // Instanciamos el controlador y llamamos a tu método
                            GestorCelulares gestorEliminar = new GestorCelulares();
                            String respuestaEliminar = gestorEliminar.eliminarCelular(idEliminar);

                            // Imprimimos la respuesta real que retorna tu método
                            System.out.println(respuestaEliminar);
                        } else {
                            System.out.println(" Operacion cancelada por el usuario.");
                        }
                        break;
                    }

                    case 5:
                        ArchivoUtils.limpiarConsola();
                        System.out.println("Regresando al menu principal...");
                        break;
                } 

            } catch (NumberFormatException e){
                System.out.println("Opcion invalida. Asegurese de ingresar un numero entero (1-5).");
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: La gama ingresada no es valida. Use unicamente: Alta, Media o Baja.");
            } catch (Exception e) {
                System.out.println("Ocurrio un error inesperado: " + e.getMessage());
            }

        } while (opcion != 5);
    }
}
