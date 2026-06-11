package view;

import utils.Validador;

import java.util.Scanner;
import utils.ArchivoUtils;

public class MenuVentas {
    public static void mostrar(Scanner sc){
        int opcion = 0;
        do {
            ArchivoUtils.limpiarConsola();
            System.out.println("\n--- MODULO DE VENTAS (FACTURACION) ---");
            System.out.println("1. Registrar Nueva Venta");
            System.out.println("2. Volver al Menu Principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch(opcion){
                   case 1:
                    ArchivoUtils.limpiarConsola();
                    System.out.println("\n[ Iniciando Nueva Venta ]");

                    // --- IDENTIFICAR CLIENTE EN LA BASE DE DATOS ---
                    String cedulaCliente = ""; 
                    model.Cliente clienteRegistrado = null;
                    controller.GestorClientes gestorClientes = new controller.GestorClientes();

                    do {
                        System.out.print("Ingrese la identificacion del cliente: ");
                        cedulaCliente = sc.nextLine();

                        if (!Validador.validarNumero(cedulaCliente)){
                            System.out.println("ERROR. El numero de identificacion debe ser numerico.");
                            continue; 
                        }

                        System.out.println("Buscando al cliente en la base de datos...");
                        clienteRegistrado = gestorClientes.buscarClientePorCedula(cedulaCliente);

                        if (clienteRegistrado == null) {
                            System.out.println(" ERROR: No se encontro ningun cliente con la identificacion [" + cedulaCliente + "].");
                            System.out.print("¿Desea intentar con otra identificacion? (S/N): ");
                            String reintentar = sc.nextLine().trim().toUpperCase();

                            if (reintentar.equals("N")) {
                                System.out.println(" Cancelando venta. Regresando al menu principal.");
                                break; // CORREGIDO: Aquí usamos break para romper el ciclo do-while y continuar al break del case 1
                            }
                        }

                    } while (clienteRegistrado == null);

                    // CORREGIDO: Si el usuario canceló la búsqueda, clienteRegistrado queda null. 
                    // Detenemos la venta antes de entrar al carrito para evitar un NullPointerException.
                    if (clienteRegistrado == null) {
                        break; 
                    }

                    // Si pasa de aquí, el cliente fue verificado con éxito
                    System.out.println(" ¡Cliente verificado exitosamente!");
                    System.out.println(" -> Cliente: " + clienteRegistrado.getNombre());
                    System.out.println(" -> Correo: " + clienteRegistrado.getCorreo());
                    System.out.println("----------------------------------------");


                    // --- BUCLE PARA AGREGAR PRODUCTOS ---
                    boolean agregandoProducto = true;
                    double subtotalAcumulado = 0.0;

                    System.out.println("\n--- CARRITO DE COMPRAS ---");
                    do {
                        int idCelular = 0;
                        boolean idValido = false;
                        do {
                            try {
                                System.out.println("Ingrese el ID del celular a vender: ");
                                idCelular = Integer.parseInt(sc.nextLine());
                                if (idCelular > 0){
                                    idValido = true;
                                } else {
                                    System.out.println("ERROR. El ID debe ser un número mayor a cero.");
                                }
                            } catch (NumberFormatException e){
                                System.out.println("ERROR. Ingrese un ID numerico valido");
                            }
                        } while (!idValido);

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
                                System.out.println(" ERROR: Ingrese un numero entero valido.");
                            }
                        } while (!cantidadValida);

                        // VALIDACIÓN DE STOCK Y DATOS EN TIEMPO REAL
                        System.out.println(" Verificando stock disponible del celular...");

                        controller.GestorCelulares gestorCelulares = new controller.GestorCelulares();
                        model.Celular celular = gestorCelulares.buscarCelularPorId(idCelular);

                        // 1. Validar si el celular existe
                        if (celular == null) {
                            System.out.println(" ERROR: El ID [" + idCelular + "] no corresponde a ningun celular en el sistema.");
                            System.out.println(" Intentelo de nuevo con un ID valido.\n");
                            continue; 
                        }

                        // 2. Validar si hay unidades suficientes
                        if (celular.getStock() < cantidad) {
                            System.out.println(" ERROR: Stock insuficiente para efectuar la operacion.");
                            System.out.println(" Celular solicitado: " + celular.getMarca() + " " + celular.getModelo());
                            System.out.println(" Stock real en inventario: " + celular.getStock() + " unidades.");
                            System.out.println(" Por favor, ingrese una cantidad permitida.\n");
                            continue; 
                        }

                        // 3. Confirmación de producto añadido
                        System.out.println(" ¡Stock verificado de forma exitosa!");
                        System.out.println(" -> Agregado: " + celular.getMarca() + " " + celular.getModelo());
                        System.out.printf(" -> Precio Unitario: $%,.2f | Unidades: %d\n", celular.getPrecio(), cantidad);

                        // CÁLCULO REAL DEL SUBTOTAL ACUMULADO
                        subtotalAcumulado += (celular.getPrecio() * cantidad);
                        
                        int nuevoStock = celular.getStock() - cantidad;
                        celular.setStock(nuevoStock);
                        
                        gestorCelulares.actualizarStock(celular.getId(), nuevoStock); 
                        System.out.println(" -> [INFO] Inventario actualizado. Nuevo stock: " + nuevoStock);

                        // Preguntar si desea continuar comprando
                        System.out.print("\n¿Desea agregar otro celular a esta venta? (S/N): ");
                        String respuesta = sc.nextLine().trim().toUpperCase();
                        if (respuesta.equals("N")) {
                            agregandoProducto = false;
                        }

                    } while (agregandoProducto);


                    // --- 3. SELECCIÓN DE ESTRATEGIA DE DESCUENTO ---
                    System.out.println("\n--- APLICACION DE DESCUENTOS ---");
                    System.out.println("1. Cliente Regular (Sin Descuento)");
                    System.out.println("2. Dia Feriado (10% de Descuento)");
                    System.out.println("3. Cliente VIP (15% de Descuento)");
                    System.out.print("Seleccione la promoción aplicable: ");

                    patron.EstrategiaDescuento estrategia = new patron.SinDescuento();

                    try {
                        int opcionDescuento = Integer.parseInt(sc.nextLine());
                        switch (opcionDescuento) {
                            case 2:
                                // CORREGIDO: Eliminados los casteos innecesarios (EstrategiaDescuento) que podían causar errores de casteo implícito
                                estrategia = new patron.DescuentoFeriado();
                                break;
                            case 3:
                                estrategia = new patron.DescuentoVip();
                                break;
                            case 1:
                            default:
                                estrategia = new patron.SinDescuento();
                                break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Entrada invalida. Se procedera sin descuentos.");
                    }

                    // --- 4. CÁLCULO TOTAL DE LA VENTA ---
                    double subtotalConDescuento = estrategia.aplicarDescuento(subtotalAcumulado);
                    double iva = subtotalConDescuento * 0.19; 
                    double totalAPagar = subtotalConDescuento + iva;

                    System.out.println("\n--- RESUMEN DE FACTURACION ---");
                    System.out.printf("Subtotal original: $%,.2f\n", subtotalAcumulado);

                    if (subtotalAcumulado > subtotalConDescuento) {
                        double ahorro = subtotalAcumulado - subtotalConDescuento;
                        System.out.printf("Ahorro por promocion: -$%,.2f\n", ahorro);
                        System.out.printf("Subtotal con descuento: $%,.2f\n", subtotalConDescuento);
                    }

                    System.out.printf("IVA (19%%): $%,.2f\n", iva);
                    System.out.printf("Total a Pagar: $%,.2f\n", totalAPagar);

                    System.out.println("\n ¡Venta procesada con exito!");
                    System.out.println("Próximamente: Guardando factura en tablas 'ventas' y 'detalle_ventas' mediante JDBC...");
                    break;
                        
                    case 2:
                        ArchivoUtils.limpiarConsola();
                        System.out.println("Regresando al menu principal...");
                        break; 

                    default:
                        System.out.println(" Opcion no valida (1-2).");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println(" Opcion invalida. Ingrese un numero entero.");
            }
                    }while (opcion != 2);

            }
        }
    




