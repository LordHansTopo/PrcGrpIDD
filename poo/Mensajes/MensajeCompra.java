
package poo.Mensajes;

public class MensajeCompra extends Mensaje{
    protected String cliente,empresa;
    private double cantidadMax;
    protected MensajeCompra(){} //Constructor vacio para la herencia

    public MensajeCompra(String Cliente, String Empresa, double CantidadMax){
        super();
        this.cliente=Cliente;
        this.empresa=Empresa;
        this.cantidadMax=CantidadMax;
    }
    
    public String getCliente() {
        return cliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public double getCantidadMax() {
        return cantidadMax;
    }
    
    public String codificaMensaje(){

        return identificador +"|"+ cliente +"|" + empresa + "|"+ cantidadMax;
    
    }

}
