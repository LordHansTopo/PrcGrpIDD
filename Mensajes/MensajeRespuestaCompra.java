
package Mensajes;

public class MensajeRespuestaCompra extends MensajeCompra{
    private boolean resultadoOp;
    private int accionesCompradas;
    private double precioAccion,cantidadRestante;

    public boolean isResultadoOp() {
        return resultadoOp;
    }

    public int getAccionesCompradas() {
        return accionesCompradas;
    }

    public double getPrecioAccion() {
        return precioAccion;
    }

    public double getCantidadRestante() {
        return cantidadRestante;
    }

    public MensajeRespuestaCompra(int ID, String Cliente, boolean resultado, int compradas, double precioAccion, double restante){
        identificador=ID;
        cliente=Cliente;
        resultadoOp=resultado;
        accionesCompradas=compradas;
        this.precioAccion=precioAccion;
        cantidadRestante=restante;
    }
    public String codificaMensaje(){
        return identificador + "|" + cliente + "|" + resultadoOp + "|" + accionesCompradas + "|" + precioAccion + "|" + cantidadRestante;
    }

}
//[5004(id)|DNI(nom)|Kokacola(emp)|true/false|2(numAcc)|250(precioAcc)|50(dinero restante)]