
package Mensajes;

public class MensajeRespuestaActualización extends MensajeActualizacion{
    public String mensaje;
    
    public String crearMensajeRespCompra(int identificador, String nombre, String empresa, int valorAct){
        
        this.mensaje= new StringBuilder().append(String.valueOf(identificador)).append("|").append(nombre).append("|")
        .append(empresa).append("|").append("true").append("|").append(String.valueOf(valorAct)).toString();

        return mensaje;
    }
}
