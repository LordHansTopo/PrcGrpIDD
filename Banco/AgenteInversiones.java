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
    public void EjecutarOperaciones(BolsaDeValores bolsa,BancoDeInversores banco){ //WIP
        for (Mensaje actual : operacionesPendientes) {
            String respuestaCodificado = bolsa.intentaOperacion(actual.codificaMensaje());
            String[] datos = Mensaje.parser(respuestaCodificado);
            if (actual instanceof MensajeCompra) {
                resultadoDeOperaciones.add(new MensajeRespuestaCompra(Integer.parseInt(datos[0]), datos[1],
                        Boolean.parseBoolean(datos[2]), Integer.parseInt(datos[3]), Double.parseDouble(datos[4]),
                        Double.parseDouble(datos[5])));
            } else if (actual instanceof MensajeVenta) {

            } else {

            }
        }
        for (Mensaje actual: resultadoDeOperaciones){
            if (actual instanceof MensajeRespuestaCompra){
                MensajeRespuestaCompra actualCast = (MensajeRespuestaCompra) actual;
                banco.ComprarAccion(actualCast.getCliente(),actualCast.getEmpresa(),actualCast.getAccionesCompradas(),
                        actualCast.getPrecioAccion(),actualCast.getCantidadRestante());
            }
            else if (actual instanceof MensajeRespuestaVenta){
                MensajeRespuestaVenta actualCast = (MensajeRespuestaVenta) actual;
                //banco.VenderAccion();
            }
            else{
                MensajeRespuestaActualizacion actualCast = (MensajeRespuestaActualizacion) actual;

                //banco.ActualizarClientes();
            }
        }
    }
}
