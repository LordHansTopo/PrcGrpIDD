
package Mensajes;

public class MensajeRespuestaVenta extends MensajeVenta{
    private String mensaje;
    
    public String crearMensajeRespVenta(int identificador, String nombre, String empresa, int numAcciones, int valor){
        if(numAcciones!=0)
            this.mensaje= new StringBuilder().append(String.valueOf(identificador)).append("|").append(nombre).append("|")
                .append(empresa).append("|").append("true").append("|").append(String.valueOf(numAcciones)).append("|")
                    .append(String.valueOf(numAcciones*valor)).toString();
        else this.mensaje=new StringBuilder().append(String.valueOf(identificador)).append("|").append(nombre).append("|")
                .append(empresa).append("|").append("false").append("|").append(String.valueOf(numAcciones)).append("|")
                .append(String.valueOf(numAcciones*valor)).toString();
        return mensaje;
    }
}
//[5004(id)|Antonio(nom)|Kokacola(emp)|true/false|2(numAcc)|500(monei a ganar)]