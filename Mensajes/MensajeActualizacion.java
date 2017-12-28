
package Mensajes;
import java.time.Instant;

public class MensajeActualizacion extends Mensaje{
    protected String cliente, empresa;
    protected MensajeActualizacion(){}
    public MensajeActualizacion(String Cliente, String Empresa){
        this.cliente=Cliente;
        this.empresa=Empresa;

    }
    
    public String codificaMensaje(){
        Instant fecha = Instant.now();
        return this.getIdentificador() +"|"+ fecha;
    }
}
