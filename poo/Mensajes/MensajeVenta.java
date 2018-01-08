
package poo.Mensajes;

public class MensajeVenta extends Mensaje{
    private String cliente, empresa;
    private int numAcc;
    public MensajeVenta(String Cliente, String Empresa, int numAcc){
      super();
      this.cliente=Cliente;
      this.empresa=Empresa;
      this.numAcc=numAcc;
    }
     public String getCliente() {
        return cliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public int getNumAcc() {
        return numAcc;
    }
    
    public String codificaMensaje(){
       
        return identificador +"|"+ cliente +"|"+ empresa +"|"+ numAcc;
    }

}
