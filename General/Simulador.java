package General;

import Bolsa.BolsaDeValores;

public class Simulador {
    //region atributos
    private InterfazDeUsuario interfaz;
    private BolsaDeValores bolsaDeValores;
    /*
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

    public BolsaDeValores getBolsaDeValores() {
        return bolsaDeValores;
    }
    public void setBolsaDeValores(BolsaDeValores bolsaDeValores) {
        this.bolsaDeValores = bolsaDeValores;
    }
    /*
    public BancoDeInversiones getBancoDeInversiones() {
        return bancoDeInversiones;
    }

    public void setBancoDeInversiones(BancoDeInversiones bancoDeInversiones) {
        this.bancoDeInversiones = bancoDeInversiones;
    }

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
        BolsaDeValores bolsa = new BolsaDeValores();
        setBolsaDeValores(bolsa);
    }
    public void comenzarSimulacion(){
        while(true) {
            switch (getInterfaz().iniciarInterfaz()) {
                case 0:
                    System.out.println("Saliendo..");
                    System.exit(0);
                    break;
                case 1:
                    //getBancoDeInversores().EstadoClientes;
                    System.out.println("WIP");
                    break;
                case 2:
                    getBolsaDeValores().EstadoBolsa();
                    System.out.println("WIP");
                    break;
                case 3:
                    //getBancoDeInversores().AñadirCliente;
                    System.out.println("WIP");
                    break;
                case 4:
                    //getBancoDeInversores().EliminarCliente;
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
                    //getBancoDeInversores().MejorarCliente;
                    System.out.println("WIP");
                    break;
                case 8:
                    //getBancoDeInversores().Recomendacion;
                    System.out.println("WIP");
                    break;
                case 9:
                    getBolsaDeValores().AñadirEmpresa();
                    System.out.println("WIP");
                    break;
                case 10:
                    getBolsaDeValores().EliminarEmpresa();
                    System.out.println("WIP");
                    break;
                case 11:
                    getBolsaDeValores().ActualizarValoresBolsa();
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
