package Excepciones;

public class ExcepcionClientePremium extends Exception{
    private String DNI;
    public ExcepcionClientePremium(String DNICliente){
        super();
        DNI=DNICliente;
    }

    public String getDNI() {
        return DNI;
    }
}
