package Bolsa;

import Excepciones.ExcepcionExistenciaEmpresa;
import General.Escaner;
import General.Utilidades;
import Mensajes.Mensaje;
import Mensajes.MensajeRespuestaCompra;

import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

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
        try(FileOutputStream file = new FileOutputStream(path)){
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(this.bolsa);
            System.out.println("Copia guardada con éxito en " + path);
        }
        catch(FileNotFoundException fnfex){
            System.out.println("Error: No se puede escribir el fichero en disco. (FileNotFoundException)");
        }
        catch(IOException ioex){
            System.out.println("Error de E/S. (IOException)");
            ioex.getCause();
            ioex.getMessage();
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
            ioex.getCause();
            ioex.getMessage();
        }
        catch(ClassNotFoundException cnfex){
            System.out.println("Error al cargar archivo (Archivo incorrecto). (ClassNotFoundException)");
        }
    }
    public boolean existeEmpresa(String nombreEmpresa){ //Usado en el banco de inversiones para controlar excepciones
        return bolsa.containsKey(nombreEmpresa);
    }
    public String intentaOperacion(String mensajeCodificado) { //WIP
        String[] datos = Mensaje.parser(mensajeCodificado);
        double valorAccion = bolsa.get(datos[2]).getValor();
        boolean operacionRealizable = Double.parseDouble(datos[3]) > valorAccion;
        int accionesCompradas = (int) (Double.parseDouble(datos[3]) / valorAccion);

        MensajeRespuestaCompra mensajeRespuestaCompra = new MensajeRespuestaCompra(Integer.parseInt(datos[0]),datos[1],
                operacionRealizable,accionesCompradas,valorAccion,
                Double.parseDouble(datos[3])-accionesCompradas*valorAccion);
        return mensajeRespuestaCompra.codificaMensaje();
    }
}
