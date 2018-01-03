package Banco;

import java.io.Serializable;

public class Persona implements Serializable{
    //region Atributos
    private String nombre;
    private String DNI;

    //endregion
    //region Constructor
    public Persona(String nombre, String DNI){
        setNombre(nombre);
        setDNI(DNI);
    }

    //endregion
    //region Getters y Setters
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    //endregion

}
