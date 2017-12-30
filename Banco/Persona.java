package Banco;

import java.io.Serializable;

public class Persona implements Serializable{
    //Atributos
    private String nombre;
    private String DNI;

    //Endregion
    //Constructor
    public Persona(String nombre, String DNI){
        setNombre(nombre);
        setDNI(DNI);
    }

    //Endregion
    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    //Endregion
    //Otros metodos
    public void printNombre(){
        System.out.print(getNombre());
    }

    public void printDNI(){
        System.out.print(getDNI());
    }

    //Endregion
}
