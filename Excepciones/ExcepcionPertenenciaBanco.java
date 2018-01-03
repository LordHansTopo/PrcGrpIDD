package Excepciones;

public class ExcepcionPertenenciaBanco extends Exception {
    private String DNI;

    public ExcepcionPertenenciaBanco(String dni,String message){
        super(message);
        DNI=dni;
    }

    public String getDNI() {
        return DNI;
    }
}
