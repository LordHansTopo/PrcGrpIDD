
package Mensajes;

import General.Utilidades;

abstract public class Mensaje {
    protected int identificador;

    protected Mensaje(){ //Genera identificador
        identificador=Utilidades.GenerarIntAleat(0,10000);
    }

    public int getIdentificador() {
        return identificador;
    }

    abstract public String codificaMensaje();
    public static String[] parser(String mensajeCodificado){
        return mensajeCodificado.split("[|]");
    }
}
