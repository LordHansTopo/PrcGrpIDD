
package Mensajes;

public class MensajeActualizacion extends Mensaje{
    protected String cliente, empresa;
    protected MensajeActualizacion(){}
    public MensajeActualizacion(String Cliente, String Empresa){
        this.cliente=Cliente;
        this.empresa=Empresa;

    }
    
    public String codificaMensaje(){
        
        return identificador +"|"+ cliente +"|" + empresa;
        //Debe devolver
        //Identificador|Fecha de solicitud (formato yyyyMMddHHmmss)

    }
}
