package Banco;

import Bolsa.BolsaDeValores;
import Excepciones.*;
import General.Escaner;
import General.Utilidades;
import Mensajes.MensajeActualizacion;
import Mensajes.MensajeCompra;
import Mensajes.MensajeVenta;

import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class BancoDeInversores implements Serializable{
    //region Atributos
    private String nombre;
    private AgenteInversiones agenteInversiones;
    private HashMap<String, Cliente> clientes;
    //endregion
    //region Constructor
    public BancoDeInversores(AgenteInversiones agente, String Nombre){
        agenteInversiones = agente;
        nombre=Nombre;
        clientes = new HashMap<String, Cliente>();
    }
    //endregion

    //region Otros metodos
    public void insertarCliente(){
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerDNI();
        boolean existeGestor = false;
        for (Cliente buscarGestores : clientes.values()){
            if (buscarGestores instanceof ClientePremium){
                if (((ClientePremium) buscarGestores).getGestor().getDNI().equals(DNI)){
                    existeGestor=true;
                    break;
                }
            }
        }
        try {
            if (!this.clientes.containsKey(DNI) && !existeGestor && !comprobarDNIAgente(DNI)) {
                //si el cliente es nuevo se pide la introduccion de datos
                System.out.println("Introduzca nombre del cliente");
                String nombre = escaner.leerString();
                System.out.println("Introduzca el saldo inicial del cliente");
                Double saldo = escaner.leerDouble();

                Cliente cliente = new Cliente(nombre, DNI, saldo);
                this.clientes.put(DNI, cliente);
                System.out.println("Cliente introducido correctamente");
            } else {
                throw new ExcepcionPertenenciaBanco(DNI,"Este cliente ya pertenece al banco.");
            }
        } catch (ExcepcionPertenenciaBanco ex) {
            System.out.println(ex.getMessage());
            System.out.println("DNI: " + ex.getDNI());
            System.out.println("DNI: " + ex.getDNI());
        }
    }

    public void eliminarCliente(){
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerDNI();
        try {
            if (this.clientes.containsKey(DNI)) {
                this.clientes.remove(DNI);
                System.out.println("Cliente con DNI: " + DNI + " Ha sido correctamente eliminado");
            } else {
                throw new ExcepcionPertenenciaBanco(DNI,"Cliente no pertenece al banco");
            }
        }
        catch (ExcepcionPertenenciaBanco ex2) {
            System.out.println(ex2.getMessage());
            System.out.println("DNI: " + ex2.getDNI());
        }
    }

    public void imprimirClientes(){
        if (clientes.isEmpty()){
            System.out.println("No hay clientes registrados.");
        }
        else {
            DecimalFormat formateadorValores = new DecimalFormat("0.00");
            System.out.println("Clientes de " + nombre + ":");
            for (Map.Entry<String, Cliente> cliente : clientes.entrySet()) {
                //bucle que en cada cliente imprime su informacion
                System.out.println("DNI: " + cliente.getKey());
                System.out.println("Nombre: " + cliente.getValue().getNombre());
                System.out.println("Saldo: " + formateadorValores.format(cliente.getValue().getSaldo()) + " €");
                if (cliente.getValue() instanceof ClientePremium) {
                    System.out.println("Cliente premium. Gestor asociado:");
                    System.out.println("DNI del Gestor: " + ((ClientePremium) cliente.getValue()).getGestor().getDNI());
                    System.out.println("Nombre del Gestor: " + ((ClientePremium) cliente.getValue()).getGestor().getNombre());
                }
                cliente.getValue().imprimirAcciones();
                System.out.print("\n");
            }
        }
    }

    public void mejorarCliente(BolsaDeValores bolsa) {
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerDNI();
        try {
            if (clientes.get(DNI) instanceof ClientePremium) throw new ExcepcionClientePremium(DNI,"Este cliente ya es premium.");
            if (clientes.containsKey(DNI)) {
                System.out.println("Inserte el DNI del Gestor a asociar (Se creará si no existe):");
                String DNIGestor = escaner.leerDNI();
                boolean existeDNIEnClientes = false;
                for (Cliente buscarDNI: clientes.values()){
                    if (buscarDNI.getDNI().equals(DNIGestor)){
                        existeDNIEnClientes=true;
                        break;
                    }
                }
                existeDNIEnClientes = comprobarDNIAgente(DNIGestor);
                if (!existeDNIEnClientes) {
                    String nombreGestor = null;
                    for (Cliente cliente : clientes.values()) {
                        if (cliente instanceof ClientePremium) {
                            GestorDeInversiones buscarGestor = ((ClientePremium) cliente).getGestor();
                            if (buscarGestor.getDNI().equalsIgnoreCase(DNIGestor)) {
                                nombreGestor = buscarGestor.getNombre();
                                System.out.println("Se asociará el gestor de nombre " + nombreGestor + ".");
                                break;
                            }
                        }
                    }
                    if (nombreGestor == null) {
                        System.out.println("Inserte el nombre del Gestor a asociar: ");
                        nombreGestor = escaner.leerString();
                        System.out.println("Gestor creado con éxito.");
                    } else throw new ExcepcionPertenenciaBanco(DNI,"Error: Este DNI pertenece a un gestor.");
                    ClientePremium clientePremium = new ClientePremium(clientes.get(DNI), new GestorDeInversiones(DNIGestor, nombreGestor, bolsa));
                    clientes.put(DNI, clientePremium); //se sustituye si existe, no hace falta eliminar antes
                    System.out.println("El cliente ha sido mejorado.");

                } else throw new ExcepcionPertenenciaBanco(DNI,"Este cliente no pertenece al banco.");
            }
        } catch (ExcepcionClientePremium ex){
            System.out.println(ex.getMessage());
            System.out.println("DNI :" + ex.getDNI());
        } catch (ExcepcionPertenenciaBanco ex) {
            System.out.println(ex.getMessage());
            System.out.println("DNI: " + ex.getDNI());
        }
    }

    public void guardarCopiaSeguridad(String path){
        if (clientes.isEmpty()) System.out.println("El banco está vacío. No se guardará copia de seguridad");
        else {
            try(FileOutputStream file = new FileOutputStream(path);
                ObjectOutputStream output = new ObjectOutputStream(file)) {
                output.writeObject(clientes);
                System.out.println("Copia guardada con éxito en " + path);
            } catch (FileNotFoundException fnfex) {
                System.out.println("Error: No se puede escribir el fichero en disco. (FileNotFoundException)");
            } catch (IOException ioex) {
                System.out.println("Error de E/S. (IOException)");
                ioex.printStackTrace();
            }
        }
    }

    public void cargarCopiaSeguridad(String path){
        try(
                InputStream file = new FileInputStream(path);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer)
        ){
            this.clientes = (HashMap<String,Cliente>) input.readObject();
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
        catch(ClassCastException csex){
            System.out.println("Error al cargar archivo (Archivo incorrecto). (ClassCastException)");
        }
    }

    public void ComprarAccion (String DNI,String Empresa, int numAcciones, double precioAccion){ //Metodo llamado por el agente
        try{
            if(this.clientes.containsKey(DNI)){
                this.clientes.get(DNI).compraPaquete(Empresa, numAcciones, precioAccion); //llamada a la funcion el class: Cliente de la compra de las acciones
            } else throw new ExcepcionPertenenciaBanco(DNI,"El cliente no existe");
        }catch (ExcepcionPertenenciaBanco ex){
            System.out.println(ex.getMessage());
            System.out.println("DNI: " + ex.getDNI());
        }

    }

    public void VenderAccion (String DNI, String Empresa, int numAcciones, double precioAccion){ //Metodo llamado por el agente
        try {
            if (this.clientes.containsKey(DNI)) {
                this.clientes.get(DNI).vendePaquete(Empresa, numAcciones, precioAccion); //llamada a la funcion el class: Cliente de la venta de las acciones
            } else throw new ExcepcionPertenenciaBanco(DNI,"El cliente no existe");
        }catch (ExcepcionPertenenciaBanco ex){
            System.out.println(ex.getMessage());
            System.out.println("DNI: " + ex.getDNI());
        }
    }

    public void ActualizarClientes(String[] empresas, Double[] precios){ //Metodo llamado por el agente que actualiza en cada cliente todas sus acciones
        for (Cliente cliente : clientes.values()){
            for (int i=0; i < empresas.length; i++){
                cliente.actualizarPaquete(empresas[i], precios[i]);
            }
        }
    }

    public void ComprarAcciones(){ // Manda mensaje de compra al broker
        try {
            Escaner escaner = new Escaner();
            System.out.println("Introduzca el DNI del cliente: ");
            String DNI = escaner.leerDNI();
            if (!clientes.containsKey(DNI)) throw new ExcepcionPertenenciaBanco(DNI,"El cliente no pertenece al banco");

            System.out.println("Introduzca el nombre de la empresa: ");
            String empresa = escaner.leerString();
            empresa = Utilidades.primeraMayus(empresa);

            System.out.println("Introduzca la cantidad máxima a invertir: ");
            double cantidadMax = escaner.leerDouble();
            if (clientes.get(DNI).getSaldo() < cantidadMax) throw new ExcepcionSaldoInsuficiente(cantidadMax,"El cliente no tiene dinero suficiente para realizar esta operación.");

            MensajeCompra operacionCompra = new MensajeCompra(DNI, empresa, cantidadMax);
            agenteInversiones.guardarOperacion(operacionCompra);
            System.out.println("Petición almacenada en la lista de peticiones del bróker.");
        }
        catch (ExcepcionPertenenciaBanco e){
            System.out.println(e.getMessage());
            System.out.println("DNI: " + e.getDNI());
        }
        catch (ExcepcionSaldoInsuficiente e){
            System.out.println(e.getMessage());
            System.out.println("Saldo mínimo necesario: " + e.getSaldoNecesario());
        }
    }

    public void VenderAcciones(){ // Manda mensaje de venta al broker
        try{
            Escaner escaner = new Escaner();
            System.out.println("Introduzca el DNI del cliente: ");
            String DNI = escaner.leerDNI();
            if (!clientes.containsKey(DNI)) throw new ExcepcionPertenenciaBanco(DNI,"Este cliente no pertenece al banco.");

            System.out.println("Introduzca el nombre de la empresa: ");
            String empresa = escaner.leerString();
            empresa = Utilidades.primeraMayus(empresa);

            System.out.println("Introduzca el número de acciones a vender: ");
            int acciones = escaner.leerInt();
            if (acciones<=0) throw new ExcepcionNoNulo();
            if (!clientes.get(DNI).suficientesAcciones(empresa,acciones)) throw new ExcepcionPaquetes();

            MensajeVenta operacionVenta = new MensajeVenta(DNI,empresa,acciones);
            agenteInversiones.guardarOperacion(operacionVenta);
            System.out.println("Petición almacenada en la lista de peticiones del bróker.");
        }
        catch (ExcepcionPertenenciaBanco ex){
            System.out.println(ex.getMessage());
            System.out.println("DNI: " + ex.getDNI());
        }
        catch (ExcepcionNoNulo ex){
            System.out.println("Error: no se puede vender este número de acciones.");
        }
        catch (ExcepcionPaquetes ex){
            System.out.println("Error: El cliente no tiene suficientes acciones de esta empresa.");
        }
    }

    public void ActualizarValoresBanco(){ // Manda mensaje de actualizacion al broker
        MensajeActualizacion operacionActualizacion = new MensajeActualizacion();
        agenteInversiones.guardarOperacion(operacionActualizacion);
        System.out.println("Petición almacenada en la lista de peticiones del bróker.");
    }

    public void Recomendacion(BolsaDeValores bolsa) {
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerDNI();
        try {
            if (!clientes.containsKey(DNI)) throw new ExcepcionPertenenciaBanco(DNI,"Este cliente no pertenece al banco.");
            if (this.clientes.get(DNI) instanceof ClientePremium) {
                ((ClientePremium) this.clientes.get(DNI)).getGestor().setBolsa(bolsa);
                ((ClientePremium) this.clientes.get(DNI)).getGestor().recomendacionBolsa();
            } else throw new ExcepcionClientePremium(DNI,"El cliente no es premium.");
        }
        catch (ExcepcionClientePremium ex) {
            System.out.println(ex.getMessage());
            System.out.println("DNI: " + ex.getDNI());
        }
        catch (ExcepcionPertenenciaBanco ex) {
            System.out.println(ex.getMessage());
            System.out.println("DNI: " + ex.getDNI());
        }
    }

    public boolean suficienteSaldo(String DNI, double Saldo){
        return clientes.get(DNI).getSaldo()>=Saldo;
    }

    public boolean comprobarDNIAgente(String DNI){
        if (this.agenteInversiones.getDNI().equals(DNI)){
            return true;
        }else{
            return false;
        }
    }
    //endregion
}
