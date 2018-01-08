package poo.Banco;

import poo.Bolsa.BolsaDeValores;
import poo.Mensajes.*;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AgenteInversiones extends Persona {

    private BolsaDeValores bolsa;
    private ArrayList<Mensaje> operacionesPendientes,resultadoDeOperaciones;

    public AgenteInversiones(String nombre, String DNI, BolsaDeValores Bolsa){
        super(nombre, DNI);
        bolsa = Bolsa;
        operacionesPendientes = new ArrayList<Mensaje>();
        resultadoDeOperaciones = new ArrayList<Mensaje>();
    }
    public void guardarOperacion(Mensaje operacion){
        operacionesPendientes.add(operacion);
    }
    public void ImprimirOperaciones(){
        if (operacionesPendientes.isEmpty()){System.out.println("No hay peticiones almacenadas.");}
        else {
            DecimalFormat formateadorValores = new DecimalFormat("0.00");
            for (Mensaje actual : operacionesPendientes) {
                if (actual instanceof MensajeCompra){
                    System.out.println("Compra:");
                    System.out.println("Identificador: " + actual.getIdentificador());
                    System.out.println("Cliente: " + ((MensajeCompra) actual).getCliente());
                    System.out.println("Empresa: " + ((MensajeCompra) actual).getEmpresa());
                    System.out.println("Cantidad a invertir: " + formateadorValores.format(((MensajeCompra) actual).getCantidadMax()) + " €\n");
                }
                else if (actual instanceof MensajeVenta){
                    System.out.println("Venta:");
                    System.out.println("Identificador: " + actual.getIdentificador());
                    System.out.println("Cliente: " + ((MensajeVenta) actual).getCliente());
                    System.out.println("Empresa: " + ((MensajeVenta) actual).getEmpresa());
                    System.out.println("Número de acciones a vender: " + ((MensajeVenta) actual).getNumAcc() + " €\n");
                }
                else {
                    System.out.println("Actualización: ");
                    System.out.println("Identificador: " + actual.getIdentificador());
                    System.out.println("Fecha de solicitud: " + ((MensajeActualizacion) actual).getFecha() + "\n");
                }
            }
        }
    }
    public void EjecutarOperaciones(BancoDeInversores banco){
        if (operacionesPendientes.isEmpty()){
            System.out.println("No hay peticiones.");
        }
        else {
            DecimalFormat formateadorValores = new DecimalFormat("0.00");
            for (Mensaje actual : operacionesPendientes) {
                String respuestaCodificado = bolsa.intentaOperacion(actual.codificaMensaje()); //Comunicación con el banco
                String[] datos = Mensaje.parser(respuestaCodificado);
                if (actual instanceof MensajeCompra) {
                    //identificador + "|" + cliente + "|" + empresa + "|" + resultadoOp + "|" + accionesCompradas
                    //+ "|" + precioAccion + "|" + cantidadRestante;
                    if (Boolean.parseBoolean(datos[3]) && banco.suficienteSaldo(datos[1],Integer.parseInt(datos[4])*Double.parseDouble(datos[5]))){
                        banco.ComprarAccion(datos[1], datos[2], Integer.parseInt(datos[4]),
                                Double.parseDouble(datos[5]));
                        bolsa.aumentarValorEmpresa(datos[2],Integer.parseInt(datos[4]));
                        System.out.println("Compra realizada. Datos:");
                        System.out.println("Cliente: " + datos[1]);
                        System.out.println("Empresa: " + datos[2]);
                        System.out.println("Número de acciones compradas: " + datos[4]);
                        System.out.println("Precio de cada acción: " + formateadorValores.format(Double.parseDouble(datos[5])) + " €");
                        System.out.println("Cantidad sobrante: " + formateadorValores.format(Double.parseDouble(datos[6])) + " €\n");
                    }
                    else{
                        System.out.println("No se ha podido realizar la compra.");
                        if(!Boolean.parseBoolean(datos[3])){
                            if (Integer.parseInt(datos[4]) == 0 && Double.parseDouble(datos[5]) == 0 && Double.parseDouble(datos[6]) == 0){
                                System.out.print("Error: La empresa no existe.\n");
                            }
                            else{
                                System.out.print("El precio de acción es superior a la cantidad recibida.\n");
                            }
                        }
                        else{
                            System.out.println("El cliente no tiene suficiente saldo.");
                        }
                    }
                    resultadoDeOperaciones.add(new MensajeRespuestaCompra(Integer.parseInt(datos[0]), datos[1], datos[2],
                            Boolean.parseBoolean(datos[3]), Integer.parseInt(datos[4]), Double.parseDouble(datos[5]),
                            Double.parseDouble(datos[6])));
                } else if (actual instanceof MensajeVenta) {
                    //identificador + "|" + cliente + "|" + empresa + "|" + resultadoOp + "|" + accionesVendidas + "|" + precioAccion
                    //        + "|" + gananciasTotales;
                    if (Boolean.parseBoolean(datos[3])) {
                        banco.VenderAccion(datos[1], datos[2], Integer.parseInt(datos[4]), Double.parseDouble(datos[5]));
                        bolsa.disminuirValorEmpresa(datos[2],Integer.parseInt(datos[4]));
                        System.out.println("Venta realizada. Datos:");
                        System.out.println("Cliente: " + datos[1]);
                        System.out.println("Empresa: " + datos[2]);
                        System.out.println("Número de acciones vendidas: " + datos[4]);
                        System.out.println("Precio de cada acción: " + formateadorValores.format(Double.parseDouble(datos[5])) + "€");
                        System.out.println("Ganancia total: " + formateadorValores.format(Double.parseDouble(datos[6])) + " €\n");
                    }
                    else{
                        System.out.println("No se ha podido realizar la venta. Esta empresa no existe.\n");
                    }
                    resultadoDeOperaciones.add(new MensajeRespuestaVenta(Integer.parseInt(datos[0]), datos[1], datos[2],
                            Boolean.parseBoolean(datos[3]), Integer.parseInt(datos[4]), Double.parseDouble(datos[5]),
                            Double.parseDouble(datos[6])));
                } else {
                    //ID|Empresa1|PrecioAccion1|...|...|EmpresaN|PrecioAccionN
                    int tamanioArrays = datos.length / 2;
                    String[] copiaNombresEmpresas = new String[tamanioArrays];
                    Double[] copiaValoresAcciones = new Double[tamanioArrays];
                    int j = 1;
                    for (int i = 1; i < datos.length; i++) {
                        copiaNombresEmpresas[i - j] = datos[i];
                        i++;
                        j++;
                        copiaValoresAcciones[i - j] = Double.parseDouble(datos[i]);
                    }
                    banco.ActualizarClientes(copiaNombresEmpresas, copiaValoresAcciones);
                    resultadoDeOperaciones.add(new MensajeRespuestaActualizacion(Integer.parseInt(datos[0]),
                            copiaNombresEmpresas, copiaValoresAcciones));
                    System.out.println("Actualización completada.\n");
                }
            }
        }
        operacionesPendientes.clear();
    }
}
