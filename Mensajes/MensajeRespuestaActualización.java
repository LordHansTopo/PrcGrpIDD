
package Mensajes;

public class MensajeRespuestaActualización extends MensajeActualizacion{
    private boolean resultadoOp;
    private double precioAccionActual;
    
    public MensajeRespuestaActualizacion(int ID, String Cliente, boolean resultado,double precioAccionActual){ //me da un error aqui, no se bien porqué
        identificador=ID;
        cliente=Cliente;
        resultadoOp=resultado;
        this.precioAccionActual=precioAccionActual;

    }
    public String codificaMensaje(){
        
        return identificador + "|" + cliente + "|" + resultadoOp + "|" + precioAccionActual;
}
}
