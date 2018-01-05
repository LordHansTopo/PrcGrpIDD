package Bolsa;

import Excepciones.ExcepcionExistenciaEmpresa;
import General.Escaner;
import General.Utilidades;
import Mensajes.Mensaje;
import Mensajes.MensajeRespuestaActualizacion;
import Mensajes.MensajeRespuestaCompra;
import Mensajes.MensajeRespuestaVenta;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.max;

public class BolsaDeValores implements Serializable{
    private HashMap<String,Empresa> bolsa;

    public BolsaDeValores(){
        this.bolsa = new HashMap<String,Empresa>();
    }
    public void AniadirEmpresa(){
        Escaner escaner = new Escaner();
        System.out.println("Introduzca el nombre de la empresa a añadir:");
        String nombre = escaner.leerString();
        System.out.println("Inserte el valor mínimo que deberían tener las acciones:");
        double minimo = escaner.leerDouble();
        System.out.println("Inserte el valor máximo que deberían tener las acciones:");
        double maximo = escaner.leerDouble();
        try {
            if (bolsa.containsKey(nombre)) throw new ExcepcionExistenciaEmpresa(nombre,"La empresa ya existe.");
            else {
                Empresa empresa = new Empresa(nombre,minimo,maximo);
                bolsa.put(nombre,empresa);
                System.out.println("La empresa ha sido introducida con éxito.");
            }
        }
        catch (ExcepcionExistenciaEmpresa e){
            System.out.println(e.getMessage());
            System.out.println("Empresa:" + e.getNombreEmpresa());
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
            else throw new ExcepcionExistenciaEmpresa(eliminarNombre,"La empresa no existe en la bolsa");
        }
        catch(ExcepcionExistenciaEmpresa e){
            System.out.println(e.getMessage());
            System.out.println("Empresa:" + e.getNombreEmpresa());
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
        Escaner escaner = new Escaner();
        System.out.println("Inserte el valor mínimo que deberían tener las acciones:");
        double valorMin = escaner.leerDouble();
        System.out.println("Inserte el valor máximo que deberían tener las acciones:");
        double valorMax = escaner.leerDouble();
        if (valorMin<valorMax) {
            bolsa.forEach((k, v) -> v.actualizarValoresEmpresa(Utilidades.GenerarNumAleat(valorMin, valorMax)));
            System.out.println("Valor de acciones de todas las empresas actualizados.");
        }
        else{
            System.out.println("Valores introducidos inválidos.");
        }
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
                ObjectInput input = new ObjectInputStream(buffer)
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
        catch(ClassCastException csex) {
            System.out.println("Error al cargar archivo (Archivo incorrecto). (ClassCastException)");
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
            //identificador +"|"+ cliente +"|"+ empresa +"|"+ numAcc;
            double precioAccion,ganancia;
            boolean esRealizable = bolsa.containsKey(datos[2]);
            if (esRealizable) {
                precioAccion = bolsa.get(datos[2]).getValor();
                ganancia = comprobarTipoMensaje*bolsa.get(datos[2]).getValor();
            }
            else{
                precioAccion = 0;
                ganancia = 0;
            }
            //identificador + "|" + cliente + "|" + empresa + "|" + resultadoOp + "|" + accionesVendidas + "|" + precioAccion
            //+ "|" + gananciasTotales;
            MensajeRespuestaVenta respuesta = new MensajeRespuestaVenta(Integer.parseInt(datos[0]),datos[1],datos[2],
                    esRealizable,comprobarTipoMensaje,precioAccion,ganancia);
            return respuesta.codificaMensaje();
        }
        catch (NumberFormatException mensajeCompra){ //Si el mensaje es MensajeCompra
            boolean esRealizable;
            int numCompradas;
            double precioAccion,cantidadRestante;
            //identificador +"|"+ cliente +"|" + empresa + "|"+ cantidadMax
            if (bolsa.containsKey(datos[2])){
                esRealizable = Double.parseDouble(datos[3]) > bolsa.get(datos[2]).getValor();
                numCompradas = (int) (Double.parseDouble(datos[3])/bolsa.get(datos[2]).getValor());
                precioAccion=bolsa.get(datos[2]).getValor();
                cantidadRestante=Double.parseDouble(datos[3])-numCompradas*(bolsa.get(datos[2]).getValor());
            }
            else{
                esRealizable=false;
                numCompradas=0;
                precioAccion=0;
                cantidadRestante=0;
            }
            //identificador + "|" + cliente + "|" + empresa + "|" + resultadoOp + "|" + accionesCompradas
            //+ "|" + precioAccion + "|" + cantidadRestante;
            MensajeRespuestaCompra respuesta= new MensajeRespuestaCompra(Integer.parseInt(datos[0]),datos[1],datos[2],
                    esRealizable,numCompradas,precioAccion, cantidadRestante);
            return respuesta.codificaMensaje();
        }
        catch (ArrayIndexOutOfBoundsException mensajeActualizacion){ //Si el mensaje es MensajeActualizacion
            //identificador + "|" + fecha.format(formateador);
            String[] nombresEmpresas = new String[bolsa.size()];
            Double[] valoresAcciones = new Double[bolsa.size()];
            int i = 0;
            for (Empresa valor : bolsa.values()){
                nombresEmpresas[i] = valor.getNombre();
                valoresAcciones[i] = valor.getValor();
                i++;
            }
            //ID|Empresa1|PrecioAccion1|...|...|EmpresaN|PrecioAccionN
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
                nombreMejorEmpresa = bolsita.getKey();
            }
        }
        return nombreMejorEmpresa;
    }
    public void aumentarValorEmpresa(String NombreEmpresa, int numAcciones){ //Metodo llamado por el agente
        double valorAccion = bolsa.get(NombreEmpresa).getValor();
        final double porcentajeMax = 1;
        double maxAumento = (valorAccion*porcentajeMax/100)*(5/numAcciones); // Aumento de un 1% si se compra solo 1 accion, el aumento disminuye conforme se compran mas acciones (5 es un valor arbitrario de la funcion)
        double aumento = Utilidades.GenerarNumAleat(1,max(5,maxAumento));
        bolsa.get(NombreEmpresa).actualizarValoresEmpresa(
                valorAccion+aumento);
    }

    public void disminuirValorEmpresa(String NombreEmpresa, int numAcciones){ //Metodo llamado por el agente
        double valorAccion = bolsa.get(NombreEmpresa).getValor();
        final double porcentajeMax = 1;
        double maxDisminucion = (valorAccion*porcentajeMax/100)*(5/numAcciones); // Aumento de un 1% si se compra solo 1 accion, el aumento disminuye conforme se compran mas acciones (5 es un valor arbitrario de la funcion)
        double aumento = Utilidades.GenerarNumAleat(1,max(5,maxDisminucion));
        bolsa.get(NombreEmpresa).actualizarValoresEmpresa(
                valorAccion-aumento);
    }
}
