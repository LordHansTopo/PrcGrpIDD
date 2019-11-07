package poo.Banco;

import java.io.Serializable;

public class Persona implements Serializable{
    //region Atributos
    private String nombre;
    private String DNI;

    //endregion
    //region Constructor
    public Persona(String Nombre, String dni){
        nombre=Nombre;
        DNI=dni;
    }

    //endregion
    //region Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getDNI() {
        return DNI;
    }

    //endregion

}
