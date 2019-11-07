
package poo.Mensajes;

public class MensajeRespuestaVenta extends MensajeCompra{
    private boolean resultadoOp;
    private int accionesVendidas;
    private double precioAccion,gananciasTotales;

    public MensajeRespuestaVenta(int ID, String Cliente, String Empresa, boolean resultado, int vendidas,double precioAccion,double totales){
        identificador=ID;
        cliente=Cliente;
        empresa=Empresa;
        resultadoOp=resultado;
        accionesVendidas=vendidas;
        this.precioAccion=precioAccion;
        gananciasTotales=totales;
    }

    public String codificaMensaje(){
        return identificador + "|" + cliente + "|" + empresa + "|" + resultadoOp + "|" + accionesVendidas + "|" + precioAccion
                + "|" + gananciasTotales;
    }

}
//identificador + "|" + cliente + "|" + empresa + "|" + resultadoOp + "|" + accionesVendidas + "|" + precioAccion
//                + "|" + gananciasTotales;