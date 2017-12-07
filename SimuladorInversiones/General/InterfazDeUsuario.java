package SimuladorInversiones.General;

import Excepciones.ExcepcionValorMenu;

import java.util.InputMismatchException;

public class InterfazDeUsuario {
    Escaner escaner;
    private int opcion;

    public void setEscaner(Escaner escaner) {
        this.escaner = escaner;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public InterfazDeUsuario() {
        Escaner escaner = new Escaner();
        this.setEscaner(escaner);
    }

    public void iniciarInterfaz() {
        while (true) {
            System.out.println("[0] - Salir");
            System.out.println("------------------------- ESTADO -------------------------");
            System.out.println("[1] - Imprimir estado de los clientes");
            System.out.println("[2] - Imprimir estado de la bolsa");
            System.out.println("------------------------- BANCO --------------------------");
            System.out.println("[3] - Añadir cliente");
            System.out.println("[4] - Eliminar cliente");
            System.out.println("[5] - Realizar copia de seguridad");
            System.out.println("[6] - Restaurar copia de seguridad");
            System.out.println("[7] - Mejorar cliente a premium");
            System.out.println("[8] - Solicita recomendación de inversión");
            System.out.println("------------------------- BOLSA --------------------------");
            System.out.println("[9] - Añadir empresa a la bolsa");
            System.out.println("[10] - Eliminar empresa de la bolsa");
            System.out.println("[11] - Actualización de valores");
            System.out.println("[12] - Realizar copia de seguridad");
            System.out.println("[13] - Restaurar copia de seguridad");
            System.out.println("----------------------- OPERACIONES ----------------------");
            System.out.println("[14] - Solicitar compra de acciones");
            System.out.println("[15] - Solicitar venta de acciones");
            System.out.println("[16] - Solicitar actualización de valores");
            System.out.println("------------------------- BRÓKER -------------------------");
            System.out.println("[17] - Imprimir operaciones pendientes");
            System.out.println("[18] - Ejecutar operaciones pendientes");
            //region switchMenu
            setOpcion(-1);
            while (getOpcion()<0 || getOpcion()>18) {
                    System.out.println("\nIntroduce un valor:");
                    this.setOpcion(escaner.leerInt());
            }
            switch (this.getOpcion()) {
                case 0:
                    System.out.println("Saliendo..");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("WIP 2");
                    break;
                case 2:
                    System.out.println("WIP 3");
                    break;
                case 3:
                    System.out.println("WIP 4");
                    break;
                case 4:
                    System.out.println("WIP");
                    break;
                case 5:
                    System.out.println("WIP");
                    break;
                case 6:
                    System.out.println("WIP");
                    break;
                case 7:
                    System.out.println("WIP");
                    break;
                case 8:
                    System.out.println("WIP");
                    break;
                case 9:
                    System.out.println("WIP");
                    break;
                case 10:
                    System.out.println("WIP");
                    break;
                case 11:
                    System.out.println("WIP");
                    break;
                case 12:
                    System.out.println("WIP");
                    break;
                case 13:
                    System.out.println("WIP");
                    break;
                case 14:
                    System.out.println("WIP");
                    break;
                case 15:
                    System.out.println("WIP");
                    break;
                case 16:
                    System.out.println("WIP");
                    break;
                case 17:
                    System.out.println("WIP");
                    break;
                case 18:
                    System.out.println("WIP");
                    break;
            }
            //endregion
        }
    }
}