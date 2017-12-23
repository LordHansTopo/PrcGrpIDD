
package Mensajes;

public class MensajeCompra extends Mensaje{
    private String mensaje;
    private String cliente,empresa;
    private double cantidadMax;

    public MensajeCompra(String Cliente, String Empresa, double CantidadMax){
        this.cliente=Cliente;
        this.empresa=Empresa;
        this.cantidadMax=CantidadMax;
    }

    public String creaMensajeCompra(int identificador, String nombre, String empresa, double dinero){
        //Mensajes.identificador++;
        return this.mensaje= new StringBuilder().append(String.valueOf(identificador)).append("|").append(nombre).append("|").append(empresa).append("|").append(String.valueOf(dinero)).toString();
    }

}
