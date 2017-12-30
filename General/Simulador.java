package General;

import Bolsa.*;
import Banco.*;

import java.io.File;

public class Simulador {
    //region atributos
    private InterfazDeUsuario interfaz;
    private BancoDeInversores bancoDeInversores;
    private BolsaDeValores bolsaDeValores;
    private AgenteInversiones agenteInversiones;
    //endregion
    public Simulador(){
        this.interfaz = new InterfazDeUsuario();
        this.bolsaDeValores = new BolsaDeValores();
        this.agenteInversiones = new AgenteInversiones("Marco Polo","123456789A",bolsaDeValores);
        this.bancoDeInversores = new BancoDeInversores(this.agenteInversiones);
        bolsaDeValores.CargarCopia("DefectoBolsa.bin"); //Cargar valores por defecto de bolsa
    }
    public void comenzarSimulacion(){
        while(true) {
            switch (interfaz.iniciarInterfaz()) {
                case 0:
                    System.out.println("Saliendo..");
                    System.exit(0);
                    break;
                case 1:
                    bancoDeInversores.imprimirClientes();
                    break;
                case 2:
                    bolsaDeValores.EstadoBolsa();
                    break;
                case 3:
                    bancoDeInversores.insertarCliente();
                    break;
                case 4:
                    bancoDeInversores.eliminarCliente();
                    break;
                case 5:
                    bancoDeInversores.guardarCopiaSeguridad("CopiaBanco.bin");
                    break;
                case 6:
                    bancoDeInversores.cargarCopiaSeguridad("CopiaBanco.bin");
                    break;
                case 7:
                    bancoDeInversores.mejorarCliente();
                    break;
                case 8:
                    //bancoDeInversores.Recomendacion();
                    System.out.println("WIP");
                    break;
                case 9:
                    bolsaDeValores.AñadirEmpresa();
                    break;
                case 10:
                    bolsaDeValores.EliminarEmpresa();
                    break;
                case 11:
                    bolsaDeValores.ActualizarValoresBolsa();
                    break;
                case 12:
                    bolsaDeValores.GuardarCopia("CopiaBolsa.bin");
                    break;
                case 13:
                    bolsaDeValores.CargarCopia("CopiaBolsa.bin");
                    break;
                case 14:
                    bancoDeInversores.ComprarAcciones(bolsaDeValores);
                    break;
                case 15:
                    bancoDeInversores.VenderAcciones(bolsaDeValores);
                    break;
                case 16:
                    bancoDeInversores.ActualizarValoresBanco();
                    break;
                case 17:
                    agenteInversiones.ImprimirOperaciones();
                    break;
                case 18:
                    agenteInversiones.EjecutarOperaciones(bancoDeInversores);
                    break;

            }
        }
    }
}
