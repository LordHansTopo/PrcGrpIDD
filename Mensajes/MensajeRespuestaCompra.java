
package Mensajes;

public class MensajeRespuestaCompra extends MensajeCompra{
    private boolean resultadoOp;
    private int accionesCompradas;
    private double precioAccion,cantidadRestante;

    public MensajeRespuestaCompra(int ID,String Cliente, boolean resultado, int compradas,double precioAccion,double restante){
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
//[5004(id)|Antonio(nom)|true/false|2(numAcc)|250(precioAcc)|50(dinero restante)]