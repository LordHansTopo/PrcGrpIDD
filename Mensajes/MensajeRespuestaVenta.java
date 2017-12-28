
package Mensajes;

public class MensajeRespuestaVenta extends MensajeCompra{
    private boolean resultadoOp;
    private int accionesVendidas;
    private double precioAccion,gananciasTotales;

    public MensajeRespuestaVenta(String Cliente, boolean resultado, int vendidas,double precioAccion,double totales){
        super();
        cliente=Cliente;
        resultadoOp=resultado;
        accionesVendidas=vendidas;
        this.precioAccion=precioAccion;
        gananciasTotales=totales;
    }
    public boolean isResultadoOp() {
        return resultadoOp;
    }

    public int getAccionesVendidas() {
        return accionesVendidas;
    }

    public double getPrecioAccion() {
        return precioAccion;
    }

    public double getGananciasTotales() {
        return gananciasTotales;
    }

    public String codificaMensaje(){
        return this.getIdentificador() + "|" + cliente + "|" + resultadoOp + "|" + accionesVendidas + "|" + precioAccion + "|" + gananciasTotales;
    }

}
//[5004(id)|Antonio(nom)|Kokacola(emp)|true/false|2(numAcc)|500(monei a ganar)]