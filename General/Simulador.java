package General;

import Bolsa.*;
import Banco.*;

public class Simulador {
    //region atributos
    private InterfazDeUsuario interfaz;
    private BancoDeInversores bancoDeInversores;
    private BolsaDeValores bolsaDeValores;
    private AgenteInversiones agenteInversiones;
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
    public BancoDeInversores getBancoDeInversores() {
        return bancoDeInversores;
    }

    public void setBancoDeInversores(BancoDeInversores bancoDeInversores) {
        this.bancoDeInversores = bancoDeInversores;
    }
    public AgenteInversiones getAgenteDeInversiones() {
        return agenteInversiones;
    }

    public void setAgenteDeInversiones(AgenteInversiones agenteInversiones) {
        this.agenteInversiones = agenteInversiones;
    }
    //endregion

    public Simulador(){
        InterfazDeUsuario interfazUsuario = new InterfazDeUsuario();
        setInterfaz(interfazUsuario);
        BolsaDeValores bolsa = new BolsaDeValores();
        setBolsaDeValores(bolsa);
        BancoDeInversores bancoDeInversores = new BancoDeInversores("Nombre");
        setBancoDeInversores(bancoDeInversores);
        getBolsaDeValores().CargarCopia("DefectoBolsa.bin");
    }
    public void comenzarSimulacion(){
        while(true) {
            switch (getInterfaz().iniciarInterfaz()) {
                case 0:
                    System.out.println("Saliendo..");
                    System.exit(0);
                    break;
                case 1:
                    getBancoDeInversores().imprimirClientes();
                    break;
                case 2:
                    getBolsaDeValores().EstadoBolsa();
                    break;
                case 3:
                    getBancoDeInversores().insertarCliente();
                    break;
                case 4:
                    getBancoDeInversores().eliminarCliente();
                    break;
                case 5:
                    getBancoDeInversores().copiaSeguridad();
                    break;
                case 6:
                    //getBancoDeInversores().CargarCopia;
                    System.out.println("WIP");
                    break;
                case 7:
                    getBancoDeInversores().mejorarCliente();
                    break;
                case 8:
                    //getBancoDeInversores().Recomendacion;
                    System.out.println("WIP");
                    break;
                case 9:
                    getBolsaDeValores().AñadirEmpresa();
                    break;
                case 10:
                    getBolsaDeValores().EliminarEmpresa();
                    break;
                case 11:
                    getBolsaDeValores().ActualizarValoresBolsa();
                    break;
                case 12:
                    getBolsaDeValores().GuardarCopia("CopiaBolsa.bin");
                    break;
                case 13:
                    getBolsaDeValores().CargarCopia("CopiaBolsa.bin");
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
