
package Mensajes;

public class MensajeCompra extends Mensaje{
    protected String cliente,empresa;
    private double cantidadMax;
    protected MensajeCompra(){} //Constructor vacio para la herencia
    public MensajeCompra(String Cliente, String Empresa, double CantidadMax){
        identificador++;
        this.cliente=Cliente;
        this.empresa=Empresa;
        this.cantidadMax=CantidadMax;
    }
    public String codificaMensaje(){
        return identificador +"|"+ cliente +"|" + empresa + "|"+ cantidadMax;
    }
    //1023|Marco Polo|Kokacola|4000
}
