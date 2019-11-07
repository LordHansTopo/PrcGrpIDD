package poo.General;

import poo.Bolsa.*;
import poo.Banco.*;

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
        this.agenteInversiones = new AgenteInversiones("Marco Polo","02749221P",bolsaDeValores);
        this.bancoDeInversores = new BancoDeInversores(this.agenteInversiones,"Banco De Ejemplo");
        bolsaDeValores.CargarCopia("DefectoBolsa.bin"); //Cargar valores por defecto de bolsa
        bancoDeInversores.cargarCopiaSeguridad("DefectoBanco.bin"); //Cargar valores por defecto del banco
    }
    public void comenzarSimulacion(){
        while(true) {
            switch (interfaz.iniciarInterfaz()) {
                case 0:
                    System.out.println("Saliendo..");
                    bolsaDeValores.GuardarCopia("DefectoBolsa.bin");
                    bancoDeInversores.guardarCopiaSeguridad("DefectoBanco.bin");
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
                    bancoDeInversores.mejorarCliente(bolsaDeValores);
                    break;
                case 8:
                    bancoDeInversores.Recomendacion(bolsaDeValores);
                    break;
                case 9:
                    bolsaDeValores.AniadirEmpresa();
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
                    bancoDeInversores.ComprarAcciones();
                    break;
                case 15:
                    bancoDeInversores.VenderAcciones();
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
