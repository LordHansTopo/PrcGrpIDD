package Excepciones;

public class ExcepcionClientePremium extends Exception{
    private String DNI;
    public ExcepcionClientePremium(String DNICliente,String msg){
        super(msg);
        DNI=DNICliente;
    }

    public String getDNI() {
        return DNI;
    }
}
