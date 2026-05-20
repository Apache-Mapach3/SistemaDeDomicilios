import java.util.Scanner;

public class SistemaDelCaso {
    private static Lista listaGeneral = new Lista();
    private static Cola colaPendientes = new Cola();
    private static Pila historialProcesados = new Pila();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- MENU EMPRESA DE DOMICILIOS ---");
            System.out.println("1. Registrar elemento");
            System.out.println("2. Ver todos los elementos registrados");
            System.out.println("3. Ver elementos pendientes");
            System.out.println("4. Procesar siguiente elemento");
            System.out.println("5. Ver historial de elementos procesados");
            System.out.println("6. Buscar elemento por código");
            System.out.println("7. Cancelar elemento pendiente");
            System.out.println("8. Deshacer último procesamiento");
            System.out.println("9. Ver cantidad de elementos");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.print("Número de Orden: "); String id = scanner.nextLine();
                        System.out.print("Cliente: "); String cliente = scanner.nextLine();
                        System.out.print("Dirección: "); String dir = scanner.nextLine();
                        System.out.print("Descripción: "); String desc = scanner.nextLine();

                        // 1. VALIDAR CAMPOS VACÍOS
                        if(id.trim().isEmpty() || cliente.trim().isEmpty() || dir.trim().isEmpty() || desc.trim().isEmpty()){
                            System.out.println("Error: Todos los campos son obligatorios.");
                            break;
                        }

                        // 2. VALIDAR DUPLICADOS
                        boolean existe = false;
                        for (int i = 0; i < listaGeneral.cuentaElementos(); i++) {
                            Domicilio dom = (Domicilio) listaGeneral.buscarDato(i);
                            if(dom.getNumeroOrden().equals(id)){
                                existe = true;
                                break;
                            }
                        }

                        if(existe){
                            System.out.println("Error: Ya existe un domicilio registrado con ese número de orden.");
                            break;
                        }

                        Domicilio nuevo = new Domicilio(id, dir, cliente, desc);
                        listaGeneral.agregar(nuevo);
                        colaPendientes.encolar(nuevo);
                        System.out.println("Domicilio registrado con éxito.");
                        break;

                    case 2:
                        if (listaGeneral.esVacia()) {
                            System.out.println("No hay registros en el sistema.");
                        } else {
                            listaGeneral.mostrarAdelante();
                        }
                        break;

                    case 3:
                        if (colaPendientes.esVacia()) {
                            System.out.println("No hay domicilios pendientes.");
                        } else {
                            Cola tempCola = new Cola();
                            while (!colaPendientes.esVacia()) {
                                Domicilio dom = (Domicilio) colaPendientes.desencolar();
                                System.out.println(dom);
                                tempCola.encolar(dom);
                            }
                            while (!tempCola.esVacia()) {
                                colaPendientes.encolar(tempCola.desencolar());
                            }
                        }
                        break;

                    case 4:
                        if (colaPendientes.esVacia()) {
                            System.out.println("No hay domicilios pendientes para procesar.");
                        } else {
                            Domicilio procesado = (Domicilio) colaPendientes.desencolar();
                            historialProcesados.apilar(procesado);
                            System.out.println("Procesando: " + procesado);
                        }
                        break;

                    case 5:
                        if (historialProcesados.esVacia()) {
                            System.out.println("El historial está vacío.");
                        } else {
                            Pila tempPila = new Pila();
                            while (!historialProcesados.esVacia()) {
                                Domicilio dom = (Domicilio) historialProcesados.desapilar();
                                System.out.println(dom);
                                tempPila.apilar(dom);
                            }
                            while (!tempPila.esVacia()) {
                                historialProcesados.apilar(tempPila.desapilar());
                            }
                        }
                        break;

                    case 6:
                        System.out.print("Ingrese el número de orden a buscar: ");
                        String buscarId = scanner.nextLine();
                        boolean encontrado = false;
                        for (int i = 0; i < listaGeneral.cuentaElementos(); i++) {
                            Domicilio dom = (Domicilio) listaGeneral.buscarDato(i);
                            if (dom.getNumeroOrden().equals(buscarId)) {
                                System.out.println("Encontrado: " + dom);
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) System.out.println("No se encontró ningún domicilio con ese código.");
                        break;

                    case 7:
                        System.out.print("Ingrese el número de orden a cancelar: ");
                        String cancelarId = scanner.nextLine();
                        Cola colaAux = new Cola();
                        boolean cancelado = false;

                        while (!colaPendientes.esVacia()) {
                            Domicilio dom = (Domicilio) colaPendientes.desencolar();
                            if (dom.getNumeroOrden().equals(cancelarId)) {
                                System.out.println("Domicilio cancelado (removido de pendientes): " + dom);
                                cancelado = true;
                            } else {
                                colaAux.encolar(dom);
                            }
                        }
                        while (!colaAux.esVacia()) {
                            colaPendientes.encolar(colaAux.desencolar());
                        }
                        if (!cancelado) System.out.println("No se encontró la orden en los pendientes.");
                        break;

                    case 8:
                        if (historialProcesados.esVacia()) {
                            System.out.println("No hay procesamientos para deshacer.");
                        } else {
                            Domicilio ultimoProcesado = (Domicilio) historialProcesados.desapilar();
                            colaPendientes.encolar(ultimoProcesado);
                            System.out.println("Se deshizo el procesamiento de la orden: " + ultimoProcesado.getNumeroOrden());
                        }
                        break;

                    case 9:
                        System.out.println("Total Registrados (Lista): " + listaGeneral.cuentaElementos());
                        System.out.println("Total Pendientes (Cola): " + colaPendientes.tamanio());
                        System.out.println("Total Procesados (Pila): " + historialProcesados.tamanio());
                        break;

                    case 10:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 10);
    }
}