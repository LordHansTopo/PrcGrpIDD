
package Mensajes;

public class MensajeRespuestaCompra extends MensajeCompra{
    private String mensaje;
    
    public String crearMensajeRespCompra(int identificador, String nombre, String empresa, int dinero, int valor){
        if(dinero>=valor)
            this.mensaje= new StringBuilder().append(String.valueOf(identificador)).append("|").append(nombre).append("|")
                    .append(empresa).append("|").append("true").append("|").append(String.valueOf((int)dinero/valor))
                    .append("|").append(valor).append(dinero-(valor*((int)dinero/valor))).toString();
        else this.mensaje=new StringBuilder().append(String.valueOf(identificador)).append("|").append(nombre).append("|")
                .append(empresa).append("|").append("false").append("|").append("0").append("|").append(String.valueOf((int)dinero/valor))
                .append("|").append(String.valueOf(dinero)).toString();
        return mensaje;
    }
}
//[5004(id)|Antonio(nom)|Kokacola(emp)|true/false|2(numAcc)|250(precioAcc)|50(dinero restante)]