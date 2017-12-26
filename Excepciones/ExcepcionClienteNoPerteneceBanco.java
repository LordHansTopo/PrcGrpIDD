package Excepciones;

public class ExcepcionClienteNoPerteneceBanco extends Exception {
    public ExcepcionClienteNoPerteneceBanco(){
        super();
    }
    public ExcepcionClienteNoPerteneceBanco(String message){
        super(message);
    }
}
