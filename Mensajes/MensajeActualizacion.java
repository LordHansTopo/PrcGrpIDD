package Mensajes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MensajeActualizacion extends Mensaje{
    protected String empresa;
    private LocalDateTime fecha;
    public MensajeActualizacion(){
        super();
        this.fecha=LocalDateTime.now();
    }

    public String getFecha() {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fecha.format(formateador);
    }

    public String codificaMensaje(){
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return identificador + "|" + fecha.format(formateador);
    }
}
