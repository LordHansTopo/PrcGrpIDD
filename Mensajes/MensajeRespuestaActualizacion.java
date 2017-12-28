
package Mensajes;
import java.util.ArrayList;
import java.time.Instant;
public class MensajeRespuestaActualizacion extends MensajeActualizacion{
    private boolean resultadoOp;
    private double precioAccionActual;
    private ArrayList<String> nombresEmpresas;
    private ArrayList<Double> valoresAcciones;
    
    public MensajeRespuestaActualizacion(String Cliente, boolean resultado,double precioAccionActual){
        super();
        cliente=Cliente;
        resultadoOp=resultado;
        this.precioAccionActual=precioAccionActual;

    }
    public String[] nombreEmpresasToArray(){
        return (String[])nombresEmpresas.toArray();
    }
    public Double[] valoresAccionesToArray(){
        return (Double[]) valoresAcciones.toArray();
    }
    public String codificaMensaje(){
       String mensaje;
       Instant fecha = Instant.now();
       mensaje=String.valueOf(this.getIdentificador());
        for(int i=0;i<nombreEmpresasToArray().length;i++){
           mensaje="|"+nombresEmpresas[i]+"|"+valoresAcciones[i]; //si alguno lo sabe corregir...
       }
       mensaje="|"+ fecha;
               return mensaje;
}
}
