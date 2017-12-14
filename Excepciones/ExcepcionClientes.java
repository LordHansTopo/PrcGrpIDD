package Excepciones;

public class ExcepcionClientes extends Exception {
    public ExcepcionClientes(){
        super();
    }
    public ExcepcionClientes(String message){
        super(message);
    }
}