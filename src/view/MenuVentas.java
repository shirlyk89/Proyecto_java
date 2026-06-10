package view;

import utils.Validador;
import patron.EstrategiaDescuento;

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

                        // --- VARIABLE AGREGADA PARA ACUMULAR EL SUBTOTAL ---
                        double subtotalAcumulado = 0.0;

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

                            // --- SIMULACIÓN DE SUMA AL SUBTOTAL ---
                            // En tu lógica final, aquí buscarías el precio del celular en BD y lo multiplicarías por la cantidad.
                            // Ejemplo: subtotalAcumulado += celular.getPrecio() * cantidad;
                            subtotalAcumulado += (500000.0 * cantidad); // Simulando un celular de $500,000

                            // Preguntar si desea continuar comprando
                            System.out.print("¿Desea agregar otro celular a esta venta? (S/N): ");
                            String respuesta = sc.nextLine().trim().toUpperCase();
                            if (respuesta.equals("N")) {
                                agregandoProducto = false;
                            }

                        } while (agregandoProducto);


                        // 3. SELECCIÓN DE ESTRATEGIA DE DESCUENTO
                        System.out.println("\n--- APLICACIÓN DE DESCUENTOS ---");
                        System.out.println("1. Cliente Regular (Sin Descuento)");
                        System.out.println("2. Día Feriado (10% de Descuento)");
                        System.out.println("3. Cliente VIP (15% de Descuento)");
                        System.out.print("Seleccione la promoción aplicable: ");

                        // Declaramos la interfaz. Por defecto, iniciamos sin descuento.
                        patron.EstrategiaDescuento estrategia = new patron.SinDescuento();

                        try {
                            int opcionDescuento = Integer.parseInt(sc.nextLine());
                            switch (opcionDescuento) {
                                case 2:
                                    estrategia = (EstrategiaDescuento) new patron.DescuentoFeriado();
                                    break;
                                case 3:
                                    estrategia = (EstrategiaDescuento) new patron.DescuentoVip();
                                    break;
                                case 1:
                                default:
                                    // Se mantiene patron.SinDescuento()
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(" Entrada inválida. Se procederá sin descuentos.");
                        }

                        // 4. CÁLCULO TOTAL DE LA VENTA USANDO POLIMORFISMO
                        // Aquí ocurre la magia del patrón Strategy: no importa qué clase se instanció arriba,
                        // todas responden al mismo método aplicarDescuento().
                        double subtotalConDescuento = estrategia.aplicarDescuento(subtotalAcumulado);
                        double iva = subtotalConDescuento * 0.19; // Calculando el 19% de IVA
                        double totalAPagar = subtotalConDescuento + iva;

                        System.out.println("\n--- RESUMEN DE FACTURACIÓN ---");
                        System.out.printf("Subtotal original: $%,.2f\n", subtotalAcumulado);

                        // Mostrar cuánto se ahorró el cliente si hubo descuento
                        if (subtotalAcumulado > subtotalConDescuento) {
                            double ahorro = subtotalAcumulado - subtotalConDescuento;
                            System.out.printf("Ahorro por promoción: -$%,.2f\n", ahorro);
                            System.out.printf("Subtotal con descuento: $%,.2f\n", subtotalConDescuento);
                        }

                        System.out.printf("IVA (19%%): $%,.2f\n", iva);
                        System.out.printf("Total a Pagar: $%,.2f\n", totalAPagar);

                        System.out.println("\n ¡Venta procesada con éxito!");
                        System.out.println("Próximamente: Guardando factura en tablas 'ventas' y 'detalle_ventas' mediante JDBC...");
                        break; 
                        
                    case 2:
                        System.out.println("Regresando al menú principal...");
                        break; 

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
    

