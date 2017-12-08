
package Mensaje;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
public class MensajeVenta {
    public MensajeVenta(){
        
    }
    public void leeOrden(String operacionesPendientes){
       try(BufferedReader br = new BufferedReader(new FileReader(operacionesPendientes))) {
            String campoLeido = br.readLine();
            int contador = 0;
            int identificador = 0;
            String nombre = "";
            String empresa = "";
            int dineroInv = 0;
            while (operacionesPendientes != null){
                int modulo = contador % 4;
                switch(modulo){
                    case 0:
                        identificador = Integer.parseInt(operacionesPendientes);
                        break;
                    case 1:
                        nombre = operacionesPendientes;
                        break;
                    case 2:
                        empresa = operacionesPendientes;
                        break;
                    case 3:
                        dineroInv = Integer.parseInt(operacionesPendientes);
                        break;
                    default:
                        break;
                }
                contador++;
                operacionesPendientes= br.readLine();
            }
       }catch (Exception ex){
            System.out.println("Error leyendo de archivo");
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
        }
    }
}
