
package Mensajes;

public class MensajeRespuestaCompra extends MensajeCompra{
    private boolean resultadoOp;
    private int accionesCompradas;
    private double precioAccion,cantidadRestante;
    
    public MensajeRespuestaCompra(int ID, String Cliente, String Empresa, boolean resultado, int compradas,double precioAccion,double restante){
        identificador=ID;
        cliente=Cliente;
        empresa=Empresa;
        resultadoOp=resultado;
        accionesCompradas=compradas;
        this.precioAccion=precioAccion;
        cantidadRestante=restante;
    }
    public String codificaMensaje(){
        return identificador + "|" + cliente + "|" + empresa + "|" + resultadoOp + "|" + accionesCompradas
                + "|" + precioAccion + "|" + cantidadRestante;
    }

}
//identificador + "|" + cliente + "|" + empresa + "|" + resultadoOp + "|" + accionesCompradas
//                + "|" + precioAccion + "|" + cantidadRestante;