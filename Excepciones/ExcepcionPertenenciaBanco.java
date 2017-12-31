package Excepciones;

public class ExcepcionPertenenciaBanco extends Exception {
    public ExcepcionPertenenciaBanco(){
        super();
    }
    public ExcepcionPertenenciaBanco(String message){
        super(message);
    }
}
