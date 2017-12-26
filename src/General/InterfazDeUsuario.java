package General;

import Excepciones.ExcepcionValorMenu;

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

    public int iniciarInterfaz() {
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
            this.setOpcion(-1);
            try {
                System.out.println("\nIntroduce un valor:");
                this.setOpcion(escaner.leerInt());
                if (getOpcion()<0 ||getOpcion()>18){
                    throw new ExcepcionValorMenu("Error: Valor introducido inválido, intenta de nuevo:");
                }
            }
            catch (ExcepcionValorMenu ex){
                ex.getMessage();
            }
            return getOpcion();
    }
}