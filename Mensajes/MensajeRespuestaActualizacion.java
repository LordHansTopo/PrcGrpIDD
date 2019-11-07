
package Mensajes;
public class MensajeRespuestaActualizacion extends MensajeActualizacion{
    private String[] nombresEmpresas;
    private Double[] valoresAcciones;
    
    public MensajeRespuestaActualizacion(int ID, String[] NombresEmpresas, Double[] ValoresAcciones){
        identificador=ID;
        nombresEmpresas= new String[NombresEmpresas.length];
        System.arraycopy(NombresEmpresas,0,nombresEmpresas,0,nombresEmpresas.length);
        valoresAcciones= new Double[ValoresAcciones.length];
        System.arraycopy(ValoresAcciones,0,valoresAcciones,0,valoresAcciones.length);
    }

    public String codificaMensaje(){
        String mensaje = null;
        StringBuilder sb = new StringBuilder();
        sb.append(identificador).append("|");
        for (int i = 0;i<nombresEmpresas.length;i++){
            mensaje = sb.append(nombresEmpresas[i]).append("|")
                    .append(valoresAcciones[i]).append("|").toString();
        }
        return mensaje;
    }
}
