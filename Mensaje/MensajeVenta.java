
package Mensajes;

public class MensajeVenta{
    private String mensaje;
    
    private String creaMensajeVenta(int identificador, String nombre, String empresa, int numAcciones){
        Mensaje.identificador++;
        return this.mensaje= new StringBuilder().append(String.valueOf(identificador)).append("|").append(nombre).append("|").append(empresa).append("|").append(String.valueOf(numAcciones)).toString();
    }

}
