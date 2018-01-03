package Excepciones;

public class ExcepcionExistenciaEmpresa extends Exception{
    private String nombreEmpresa;
    public ExcepcionExistenciaEmpresa(String NombreEmpresa,String msg){
        super(msg);
        nombreEmpresa=NombreEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
}
