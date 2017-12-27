
package Mensajes;

import General.Utilidades;

abstract public class Mensaje {
    private int identificador;

    public int getIdentificador() {
        return this.identificador;
    }

    public Mensaje(){
        identificador= Utilidades.GenerarIntAleat(10000);
    }
    abstract public String codificaMensaje();
    public static String[] parser(String mensajeCodificado){
        return mensajeCodificado.split("|");
    }
}