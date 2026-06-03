package view;

import utils.Validador;

import java.util.Scanner;

public class MenuVentas {
    public static void mostrar(Scanner sc){
        int opcion = 0;
        do {
            System.out.println("\n--- MÓDULO DE VENTAS (FACTURACIÓN) ---");
            System.out.println("1. Registrar Nueva Venta");
            System.out.println("2. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch(opcion){
                case 1:
                    System.out.println("\n[ Iniciando Nueva Venta ]");

                    // IDENTIFICAR CLIENTE
                    String cedulaCliente;
                    do {
                        System.out.println("Ingrese la identificacion del cliente: ");
                        cedulaCliente = sc.nextLine();
                        if (!Validador.validarNumero(cedulaCliente)){
                            System.out.println("ERROR. El numero de identificacion debe ser numerico");
                        }
                    }while (!Validador.validarNumero(cedulaCliente));
                    System.out.println("Buscando al cliente en la base de datos....");

                    // BUCLE PARA AGREGAR PRODUCTOS

                    boolean agregandoProducto = true;
                    System.out.println("---CARRITO DE COMPRAS---");
                    do {
                        int idCelular = 0;
                        boolean idValido = false;
                        do {
                            try {
                                System.out.println("Ingrese el ID del celular a vender: ");
                                idCelular = Integer.parseInt(sc.nextLine());
                                if (idCelular > 0){
                                    idValido = true;
                                }else {
                                    System.out.println("ERROR. El ID debe ser un numero");
                                }
                            }catch (NumberFormatException e){
                                System.out.println("ERROR. Ingrese un ID numerico valido");
                            }
                        }while (!idValido);
                        int cantidad = 0;
                        boolean cantidadValida = false;
                        do {
                            try {
                                System.out.print("Cantidad de unidades: ");
                                cardinalidad:
                                cantidad = Integer.parseInt(sc.nextLine());
                                if (Validador.validarStock(cantidad)) {
                                    cantidadValida = true;
                                } else {
                                    System.out.println(" ERROR: La cantidad debe ser mayor a 0.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(" ERROR: Ingrese un número entero válido.");
                            }
                        } while (!cantidadValida);

                        System.out.println(" Verificando stock disponible del celular... (Próximamente)");
                        System.out.println(" Celular agregado al carrito temporal.");

                        // Preguntar si desea continuar comprando
                        System.out.print("¿Desea agregar otro celular a esta venta? (S/N): ");
                        String respuesta = sc.nextLine().trim().toUpperCase();
                        if (respuesta.equals("N")) {
                            agregandoProducto = false;
                        }

                    } while (agregandoProducto);

                    // 3. CÁLCULO TOTAL DE LA VENTA (Simulación)
                    System.out.println("\n--- RESUMEN DE FACTURACIÓN ---");
                    System.out.println("Subtotal (Sin IVA): $X.XXX (Calculado con lógica)");
                    System.out.println("IVA (19%): $X.XXX");
                    System.out.println("Total a Pagar: $X.XXX");

                    System.out.println("\n ¡Venta procesada con éxito!");
                    System.out.println("Próximamente: Guardando factura en tablas 'ventas' y 'detalle_ventas' mediante JDBC...");
                    break; // Cierra el case 1

                    case 2:
                        System.out.println("Regresando al menú principal...");
                        break; // Cierra el case 2

                    default:
                        System.out.println(" Opción no válida (1-2).");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println(" Opción inválida. Ingrese un número entero.");
            }
                    }while (opcion != 2);

            }
        }
    

