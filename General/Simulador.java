package General;

import Banco.*;

import java.io.IOException;

public class Simulador {
    //region atributos
    private InterfazDeUsuario interfaz;
    private Banco banco = new Banco("Nombre");

    /*
    private BolsaDeValores bolsaDeValores;
    private BancoDeInversiones bancoDeInversiones;
    private AgenteDeInversiones agenteDeInversiones;
    */
    //endregion
    //region Getters & Setters
    public InterfazDeUsuario getInterfaz() {
        return interfaz;
    }

    public void setInterfaz(InterfazDeUsuario interfaz) {
        this.interfaz = interfaz;
    }
    /*
    public BolsaDeValores getBolsaDeValores() {
        return bolsaDeValores;
    }

    public void setBolsaDeValores(BolsaDeValores bolsaDeValores) {
        this.bolsaDeValores = bolsaDeValores;
    }
    */
    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
    /*
    public AgenteDeInversiones getAgenteDeInversiones() {
        return agenteDeInversiones;
    }

    public void setAgenteDeInversiones(AgenteDeInversiones agenteDeInversiones) {
        this.agenteDeInversiones = agenteDeInversiones;
    }
    */
    //endregion
    public Simulador(){
        InterfazDeUsuario interfazUsuario = new InterfazDeUsuario();
        setInterfaz(interfazUsuario);
    }
    public void comenzarSimulacion(){
        while(true) {
            switch (getInterfaz().iniciarInterfaz()) {
                case 0:
                    System.out.println("Saliendo..");
                    System.exit(0);
                    break;
                case 1:
                    getBanco().imprimirClientes();
                    System.out.println("WIP");
                    break;
                case 2:
                    //getBolsaDeValores().EstadoBolsa;
                    System.out.println("WIP");
                    break;
                case 3:
                    try {
                        getBanco().insertarCliente();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("WIP");
                    break;
                case 4:
                    getBanco().eliminarCliente();
                    System.out.println("WIP");
                    break;
                case 5:
                    //getBancoDeInversores().GuardarCopia;
                    System.out.println("WIP");
                    break;
                case 6:
                    //getBancoDeInversores().CargarCopia;
                    System.out.println("WIP");
                    break;
                case 7:
                    getBanco().mejorarCliente();
                    System.out.println("WIP");
                    break;
                case 8:
                    //getBancoDeInversores().Recomendacion;
                    System.out.println("WIP");
                    break;
                case 9:
                    //getBolsaDeValores().AñadirEmpresa;
                    System.out.println("WIP");
                    break;
                case 10:
                    //getBolsaDeValores().EliminarEmpresa;
                    System.out.println("WIP");
                    break;
                case 11:
                    //getBolsaDeValores().ActualizarValoresBolsa;
                    System.out.println("WIP");
                    break;
                case 12:
                    //getBolsaDeValores().GuardarCopia;
                    System.out.println("WIP");
                    break;
                case 13:
                    //getBolsaDeValores().CargarCopia;
                    System.out.println("WIP");
                    break;
                case 14:
                    //getBancoDeInversiones().ComprarAcciones;
                    System.out.println("WIP");
                    break;
                case 15:
                    //getBancoDeInversiones().VenderAcciones;
                    System.out.println("WIP");
                    break;
                case 16:
                    //getBancoDeInversiones().ActualizarValoresBanco;
                    System.out.println("WIP");
                    break;
                case 17:
                    //getAgenteDeInversiones().ImprimirOperaciones;
                    System.out.println("WIP");
                    break;
                case 18:
                    //getBancoDeInversiones().EjecutarOperaciones;
                    System.out.println("WIP");
                    break;
            }
        }
    }
}
