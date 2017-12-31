package Bolsa;

import Excepciones.ExcepcionExistenciaEmpresa;
import General.Escaner;
import General.Utilidades;
import Mensajes.Mensaje;
import Mensajes.MensajeRespuestaActualizacion;
import Mensajes.MensajeRespuestaCompra;
import Mensajes.MensajeRespuestaVenta;

import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BolsaDeValores implements Serializable{
    private HashMap<String,Empresa> bolsa;

    public BolsaDeValores(){
        this.bolsa = new HashMap<String,Empresa>();
    }
    public void AñadirEmpresa(){
        Escaner escaner = new Escaner();
        System.out.println("Introduzca el nombre de la empresa a añadir:");
        String nombre = escaner.leerString();
        try {
            if (bolsa.containsKey(nombre)) throw new ExcepcionExistenciaEmpresa();
            else {
                Empresa empresa = new Empresa(nombre);
                bolsa.put(nombre,empresa);
                System.out.println("La empresa ha sido introducida con éxito.");
            }
        }
        catch (ExcepcionExistenciaEmpresa e){
            System.out.println("La empresa ya existe.");
        }
    }

    public void EliminarEmpresa(){
        Escaner escaner = new Escaner();
        System.out.println("Introduzca el nombre de la empresa a eliminar:");
        String eliminarNombre = escaner.leerString();
        try{
            if (bolsa.containsKey(eliminarNombre)){
                bolsa.remove(eliminarNombre);
                System.out.println("Empresa eliminada con éxito.");
            }
            else throw new ExcepcionExistenciaEmpresa();
        }
        catch(ExcepcionExistenciaEmpresa e){
            System.out.println("La empresa no existe en la bolsa");
        }
    }
    public void EstadoBolsa(){
        if (!this.bolsa.isEmpty()) {
            bolsa.forEach((k,v) -> v.imprimirInfo());
        }
        else{
            System.out.println("La bolsa no contiene empresas.");
        }
    }
    public void ActualizarValoresBolsa(){
        bolsa.forEach((k,v) -> v.actualizarValoresEmpresa());
        System.out.println("Valor de acciones de todas las empresas actualizados.");
    }
    public void GuardarCopia(String path){
        if (bolsa.isEmpty()) System.out.println("El banco está vacío. No se guardará copia de seguridad");
        else {
            try (FileOutputStream file = new FileOutputStream(path);
                 ObjectOutputStream output = new ObjectOutputStream(file)) {
                output.writeObject(this.bolsa);
                System.out.println("Copia guardada con éxito en " + path);
            } catch (FileNotFoundException fnfex) {
                System.out.println("Error: No se puede escribir el fichero en disco. (FileNotFoundException)");
            } catch (IOException ioex) {
                System.out.println("Error de E/S. (IOException)");
            }
        }
    }
    public void CargarCopia(String path){
        try(
                InputStream file = new FileInputStream(path);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);
        ){
            this.bolsa = (HashMap<String,Empresa>) input.readObject();
            System.out.println("Copia cargada con éxito en " + path);
        }
        catch(FileNotFoundException fnfex){
            System.out.println("Error: No se puede leer el fichero o no existe. (FileNotFoundException)");
        }
        catch(IOException ioex){
            System.out.println("Error de E/S (IOException)");
        }
        catch(ClassNotFoundException cnfex){
            System.out.println("Error al cargar archivo (Archivo incorrecto). (ClassNotFoundException)");
        }
    }
    public boolean existeEmpresa(String nombreEmpresa){ //Usado en el banco de inversiones para controlar excepciones
        return bolsa.containsKey(nombreEmpresa);
    }
    public String intentaOperacion(String mensajeCodificado) {
        String[] datos = Mensaje.parser(mensajeCodificado);
        try{ //Comprobar tipo de mensaje
            int comprobarTipoMensaje = Integer.parseInt(datos[3]);
            //Si el mensaje es MensajeVenta
            MensajeRespuestaVenta respuesta = new MensajeRespuestaVenta(Integer.parseInt(datos[0]),datos[1],datos[2],
                    (bolsa.get(datos[2]).getValor()!=0),comprobarTipoMensaje,bolsa.get(datos[2]).getValor(),
                    (comprobarTipoMensaje*bolsa.get(datos[2]).getValor()));
            return respuesta.codificaMensaje();
        }
        catch (NumberFormatException mensajeCompra){ //Si el mensaje es MensajeCompra
            boolean esRealizable = Double.parseDouble(datos[3]) < bolsa.get(datos[2]).getValor();
            int numCompradas = (int) (Double.parseDouble(datos[3])/bolsa.get(datos[2]).getValor());
            MensajeRespuestaCompra respuesta= new MensajeRespuestaCompra(Integer.parseInt(datos[0]),datos[1],datos[2],
                    esRealizable,numCompradas,bolsa.get(datos[2]).getValor(),
                    (Double.parseDouble(datos[3])-numCompradas*(bolsa.get(datos[2]).getValor())));
            return respuesta.codificaMensaje();
        }
        catch (ArrayIndexOutOfBoundsException mensajeActualizacion){ //Si el mensaje es MensajeActualizacion
            String[] nombresEmpresas = new String[bolsa.size()];
            Double[] valoresAcciones = new Double[bolsa.size()];
            int i = 0;
            for (Empresa valor : bolsa.values()){
                nombresEmpresas[i] = valor.getNombre();
                valoresAcciones[i] = valor.getValor();
                i++;
            }
            MensajeRespuestaActualizacion respuesta = new MensajeRespuestaActualizacion(Integer.parseInt(datos[0]),
                    nombresEmpresas,valoresAcciones);
            return respuesta.codificaMensaje();
        }
    }


    public String empresaMayorIncremento(){
        double mayorIncremento = 0.0;
        String nombreMejorEmpresa = null;
        for (Map.Entry<String, Empresa> bolsita : bolsa.entrySet()){
            if (bolsita.getValue().getIncremento() >= mayorIncremento){
                mayorIncremento = bolsita.getValue().getIncremento();
                nombreMejorEmpresa = bolsita.getValue().getNombre();
            }
        }
        return nombreMejorEmpresa;
    }


}
