
package Mensajes;

public class MensajeRespuestaVenta extends MensajeCompra{
    private boolean resultadoOp;
    private int accionesVendidas;
    private double precioAccion,gananciasTotales;

    public MensajeRespuestaVenta(int ID,String Cliente, boolean resultado, int vendidas,double precioAccion,double totales){
        identificador=ID;
        cliente=Cliente;
        resultadoOp=resultado;
        accionesVendidas=vendidas;
        this.precioAccion=precioAccion;
        gananciasTotales=totales;
    }
    public String codificaMensaje(){
        return identificador + "|" + cliente + "|" + resultadoOp + "|" + accionesVendidas + "|" + precioAccion + "|" + gananciasTotales;
    }

}
//[5004(id)|Antonio(nom)|Kokacola(emp)|true/false|2(numAcc)|500(monei a ganar)]