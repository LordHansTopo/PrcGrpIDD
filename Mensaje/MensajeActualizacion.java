
package Mensajes;

public class MensajeActualizacion {
    private String mensaje;
    
    private String creaMensajeCompra(int identificador, String nombre, String empresa, int valorAnt){
        
        return this.mensaje= new StringBuilder().append(String.valueOf(identificador)).append("|").append(nombre).append("|").append(empresa).append("|").append(String.valueOf(valorAnt)).toString();
       
    }
}
