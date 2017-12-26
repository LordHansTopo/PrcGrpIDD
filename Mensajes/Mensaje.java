
package Mensajes;

abstract public class Mensaje {
    protected static int identificador=0;

    abstract public String codificaMensaje();
    public static String[] parser(String mensajeCodificado){
        return mensajeCodificado.split("|");
    }
}