package Banco;

import Bolsa.BolsaDeValores;
import Excepciones.ExcepcionClientes;
import Excepciones.ExcepcionPaquetes;
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
    public void ImprimirOperaciones(){
        if (operacionesPendientes.isEmpty()){System.out.println("No hay peticiones almacenadas.");}
        else {
            for (Mensaje actual : operacionesPendientes) {
                if (actual instanceof MensajeCompra){
                    System.out.println("Compra:");
                    System.out.println("Identificador: " + actual.getIdentificador());
                    System.out.println("Cliente: " + ((MensajeCompra) actual).getCliente());
                    System.out.println("Empresa: " + ((MensajeCompra) actual).getEmpresa());
                    System.out.println("Cantidad a invertir: " + ((MensajeCompra) actual).getCantidadMax() + "\n");
                }
                else if (actual instanceof MensajeVenta){
                    System.out.println("Venta:");
                    System.out.println("Identificador: " + actual.getIdentificador());
                    System.out.println("Cliente: " + ((MensajeVenta) actual).getCliente());
                    System.out.println("Empresa: " + ((MensajeVenta) actual).getEmpresa());
                    System.out.println("Número de acciones a vender: " + ((MensajeVenta) actual).getNumAcc());
                }
                else {
                    //WIP actualizacion
                }
            }
        }
    }
    public void EjecutarOperaciones(BolsaDeValores bolsa,BancoDeInversores banco){ //WIP
        for (Mensaje actual : operacionesPendientes) {
            String respuestaCodificado = bolsa.intentaOperacion(actual.codificaMensaje());
            String[] datos = Mensaje.parser(respuestaCodificado);
            if (actual instanceof MensajeCompra) {
                resultadoDeOperaciones.add(new MensajeRespuestaCompra(datos[1], datos[2], Boolean.parseBoolean(datos[3]),
                        Integer.parseInt(datos[4]), Double.parseDouble(datos[5]), Double.parseDouble(datos[6])));
            } else if (actual instanceof MensajeVenta) {
                resultadoDeOperaciones.add(new MensajeRespuestaVenta(datos[1], datos[2], Boolean.parseBoolean(datos[3]),
                        Integer.parseInt(datos[4]), Double.parseDouble(datos[5]), Double.parseDouble(datos[6])));
            } else {
                //resultadoDeOperaciones.add(new MensajeRespuestaActualizacion());
            }
        }
        for (Mensaje actual: resultadoDeOperaciones){ //Resolución de operaciones
            if (actual instanceof MensajeRespuestaCompra) {
                MensajeRespuestaCompra actualCast = (MensajeRespuestaCompra) actual;
                try {
                    if (actualCast.isResultadoOp())
                        banco.ComprarAccion(actualCast.getCliente(), actualCast.getEmpresa(),
                                actualCast.getAccionesCompradas(), actualCast.getPrecioAccion(), actualCast.getCantidadRestante());
                }
                catch (ExcepcionClientes ex){
                    System.out.println(ex.getMessage());
                }
            }
            else if (actual instanceof MensajeRespuestaVenta){
                try {
                    MensajeRespuestaVenta actualCast = (MensajeRespuestaVenta) actual;
                    if (actualCast.isResultadoOp()) banco.VenderAccion(actualCast.getCliente(), actualCast.getEmpresa(),
                            actualCast.getAccionesVendidas(), actualCast.getGananciasTotales());
                }
                catch (ExcepcionPaquetes ex){
                    System.out.println(ex.getMessage());
                }
            }
            else{
                MensajeRespuestaActualizacion actualCast = (MensajeRespuestaActualizacion) actual;
                banco.ActualizarClientes(actualCast.nombreEmpresasToArray(),actualCast.valoresAccionesToArray());
            }
        }
        System.out.println("Operaciones ejecutadas.");
    }
}
