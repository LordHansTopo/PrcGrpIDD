package Excepciones;

public class ExcepcionDNIInvalido extends Exception {
    private String DNIErroneo;
    public ExcepcionDNIInvalido(String DNIError){
        super();
        DNIErroneo=DNIError;
    }

    public String getDNIErroneo() {
        return DNIErroneo;
    }
}
