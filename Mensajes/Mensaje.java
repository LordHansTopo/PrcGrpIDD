
package Mensajes;

import General.Utilidades;

abstract public class Mensaje {
    protected int identificador;

    public Mensaje(){ //Genera identificador (Debatir si es la forma correcta de generar uno)
        identificador=Utilidades.GenerarIntAleat(100000);
    }

    public int getIdentificador() {
        return identificador;
    }

    abstract public String codificaMensaje();
    public static String[] parser(String mensajeCodificado){
        return mensajeCodificado.split("|");
    }
}