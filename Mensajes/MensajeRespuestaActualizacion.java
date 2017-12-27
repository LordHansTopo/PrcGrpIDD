
package Mensajes;

import java.util.ArrayList;

public class MensajeRespuestaActualizacion extends MensajeActualizacion{
    private boolean resultadoOp;
    private double precioAccionActual;
    private ArrayList<String> nombresEmpresas;
    private ArrayList<Double>  valoresAcciones;
    
    public MensajeRespuestaActualizacion(String Cliente, boolean resultado,double precioAccionActual){ //la clase tenia tilde en actualizacion y el constructor no, solucionado
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
        
        return this.getIdentificador() + "|" + cliente + "|" + resultadoOp + "|" + precioAccionActual;
        //El mensaje codificado debe devolver lo siguiente:
        //Identificador|Empresa1|ValorDeAccion1|Empresa2|ValorDeAccion2|...|EmpresaN|ValorDeAccionN|Fecha de respuesta String (formato yyyyMMddHHmmss)
}
}
