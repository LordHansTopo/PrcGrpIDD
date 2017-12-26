
package Mensajes;

public class MensajeVenta extends Mensaje{
    protected String cliente, empresa;
    private double precioAcc;
    protected MensajeVenta(){} 
    public MensajeVenta(String Cliente, String Empresa, double PrecioAcc){
      identificador++;
      this.cliente=Cliente;
      this.empresa=Empresa;
      this.precioAcc=PrecioAcc;
    }
            
    public String codificaMensaje(){
       
        return identificador +"|"+ cliente +"|"+ empresa +"|"+ precioAcc;
    }

}
