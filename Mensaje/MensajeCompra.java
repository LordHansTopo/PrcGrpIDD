
package Mensajes;

public class MensajeCompra {
    private String mensaje;
    
    private String creaMensajeCompra(int identificador, String nombre, String empresa, int dinero){
        Mensaje.identificador++;
        return this.mensaje= new StringBuilder().append(String.valueOf(identificador)).append("|").append(nombre).append("|").append(empresa).append("|").append(String.valueOf(dinero)).toString();
    }
}
