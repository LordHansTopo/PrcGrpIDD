
package Mensajes;

public class MensajeVenta extends Mensaje{
    private String mensaje;
    
    private String creaMensajeVenta(int identificador, String nombre, String empresa, int numAcciones){
        //Mensajes.identificador++;
        return this.mensaje= new StringBuilder().append(String.valueOf(identificador)).append("|").append(nombre).append("|").append(empresa).append("|").append(String.valueOf(numAcciones)).toString();
    }

}
