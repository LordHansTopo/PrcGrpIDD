
package Mensajes;

abstract public class Mensaje {
    private int identificador;
    
    public int getIdentificador() {
        return this.identificador;
    }
    abstract public String codificaMensaje();
    public static String[] parser(String mensajeCodificado){
        return mensajeCodificado.split("|");
    }
}