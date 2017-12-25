package Banco;

import Bolsa.BolsaDeValores;
import Mensajes.*;
import java.util.ArrayList;

public class AgenteInversiones extends Persona {

    private ArrayList<Mensaje> operacionesPendientes,resultadoDeOperaciones;

    public AgenteInversiones(String nombre, String DNI){
        super(nombre, DNI);
        operacionesPendientes = new ArrayList<Mensaje>();
        resultadoDeOperaciones = new ArrayList<Mensaje>();
    }
    public void guardarOperacion(Mensaje operacion){
        operacionesPendientes.add(operacion);
    }
    public void EjectuarOperaciones(BolsaDeValores bolsa){ //WIP
        for (Mensaje actual : operacionesPendientes){
            String respuestaCodificado = bolsa.intentaOperacion(actual.codificaMensaje());
            Mensaje.parser(respuestaCodificado);
        }
    }
}
