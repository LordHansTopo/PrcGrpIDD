package Banco;

import Mensajes.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;

public class AgenteInversiones extends Persona {

    private ArrayList<Mensaje> operacionesPendientes,resultadoDeOperaciones;

    public AgenteInversiones(String nombre, String DNI){
        super(nombre, DNI);
        operacionesPendientes = new ArrayList<Mensaje>();
        resultadoDeOperaciones = new ArrayList<Mensaje>();
    }
    public void guardarOperacionCompra(String Cliente, String Empresa, double cantidadMax){
        Mensaje operacionCompra = new MensajeCompra(Cliente,Empresa,cantidadMax);
        operacionesPendientes.add(operacionCompra);
    }
}
