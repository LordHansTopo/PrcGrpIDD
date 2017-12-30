package Banco;

import Bolsa.BolsaDeValores;
import Excepciones.*;
import General.Escaner;
import General.Utilidades;
import Mensajes.MensajeActualizacion;
import Mensajes.MensajeCompra;
import Mensajes.MensajeVenta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.io.*;
import java.util.Map;


public class BancoDeInversores implements Serializable{
    //region Atributos
    private AgenteInversiones agenteInversiones;
    private HashMap<String, Cliente> clientes;

    //endregion
    //region Constructor
    public BancoDeInversores(AgenteInversiones agente){
        agenteInversiones = agente;
        clientes = new HashMap<String, Cliente>();
    }
    //endregion

    //region Otros metodos
    public void insertarCliente(){
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();
        Boolean validar = Utilidades.validarDNI(DNI);
        try {
            if (validar) {
                try {
                    if (!this.clientes.containsKey(DNI)) {
                        System.out.println("Introduzca nombre del cliente");
                        String nombre = escaner.leerString();
                        System.out.println("Introduzca el saldo inicial del cliente");
                        Double saldo = escaner.leerDouble();

                        Cliente cliente = new Cliente(nombre, DNI, saldo);
                        this.clientes.put(DNI, cliente);
                        System.out.println("Cliente introducido correctamente");
                    } else {
                        throw new ExcepcionClienteNoPerteneceBanco("Este cliente ya pertenece al banco.");
                    }
                } catch (ExcepcionClienteNoPerteneceBanco ex2) {
                    System.out.println(ex2.getMessage());
                }
            } else {
                throw new ExcepcionClientes("Valor de DNI no valido");
            }
        }catch (ExcepcionClientes ex1){
            System.out.println(ex1.getMessage());
        }
    }

    public void eliminarCliente(){
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();
        Boolean validar = Utilidades.validarDNI(DNI);
        try {
            if (validar) {
                try {
                    if (this.clientes.containsKey(DNI)) {
                        this.clientes.remove(DNI);
                        System.out.println("Cliente con DNI: " + DNI + " Ha sido correctamente eliminado");
                    } else {
                        throw new ExcepcionClienteNoPerteneceBanco("Cliente no pertenece al banco");
                    }
                } catch (ExcepcionClienteNoPerteneceBanco ex2) {
                    System.out.println(ex2.getMessage());
                }
            } else {
                throw new ExcepcionClientes("Valor de DNI no valido");
            }
        }catch (ExcepcionClientes ex1){
            System.out.println(ex1.getMessage());
        }
    }

    public void imprimirClientes(){
        if (clientes.isEmpty()) System.out.println("No hay clientes registrados.");
        else {
            for (Map.Entry<String, Cliente> cliente : clientes.entrySet()) {
                System.out.println("DNI: " + cliente.getKey());
                System.out.println("Nombre: " + cliente.getValue().getNombre());
                System.out.println("Saldo: " + cliente.getValue().getSaldo() + " €");
                if (cliente.getValue() instanceof ClientePremium) {
                    System.out.println("Cliente premium. Gestor asociado:");
                    System.out.println("DNI del Gestor: " + ((ClientePremium) cliente.getValue()).getGestor().getDNI());
                    System.out.println("Nombre del Gestor: " + ((ClientePremium) cliente.getValue()).getGestor().getNombre());
                }
                System.out.print("\n");
            }
        }
    }

    public void mejorarCliente() {
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();
        Boolean validar = Utilidades.validarDNI(DNI);
        try {
            if (validar) {
                try {
                    if (clientes.containsKey(DNI)) {
                        System.out.println("Inserte el DNI del Gestor a asociar (Se creará si no existe):");
                        String DNIGestor = escaner.leerString();
                        if (!Utilidades.validarDNI(DNIGestor)) throw new ExcepcionClientes("Valor de DNI no válido.");

                        String nombreGestor = null;
                        for (Map.Entry<String,Cliente> cliente : clientes.entrySet()){
                            if(cliente.getValue() instanceof ClientePremium) {
                                GestorDeInversiones buscarGestor = ((ClientePremium) cliente.getValue()).getGestor();
                                if (buscarGestor.getDNI().equalsIgnoreCase(DNIGestor)){
                                    nombreGestor = buscarGestor.getNombre();
                                    break;
                                }
                            }
                        }
                        if (nombreGestor==null){
                            System.out.println("Inserte el nombre del Gestor a asociar: ");
                            nombreGestor = escaner.leerString();
                        }
                        ClientePremium clientePremium = new ClientePremium(clientes.get(DNI), new GestorDeInversiones(DNIGestor,nombreGestor));
                        clientes.put(DNI,clientePremium); //Se sustituye si existe, no hace falta eliminar antes
                    } else throw new ExcepcionClienteNoPerteneceBanco("Este cliente no pertenece al banco.");
                }
                catch (ExcepcionClienteNoPerteneceBanco ex) {
                    System.out.println(ex.getMessage());
                }
            } else throw new ExcepcionClientes("Valor de DNI no válido.");
        }
        catch(ExcepcionClientes ex){
            System.out.println(ex.getMessage());
        }
    }

    public void guardarCopiaSeguridad(String path){
        if (clientes.isEmpty()) System.out.println("El banco está vacío. No se guardará copia de seguridad");
        else {
            try (FileOutputStream file = new FileOutputStream(path);
                 ObjectOutputStream output = new ObjectOutputStream(file);) {
                output.writeObject(clientes);
                System.out.println("Copia guardada con éxito en " + path);
            } catch (FileNotFoundException fnfex) {
                System.out.println("Error: No se puede escribir el fichero en disco. (FileNotFoundException)");
            } catch (IOException ioex) {
                System.out.println("Error de E/S. (IOException)");
                System.out.println(ioex.getCause());
                System.out.println(ioex.getMessage());
                ioex.printStackTrace();
            }
        }
    }

    public void cargarCopiaSeguridad(String path){
        try(
                InputStream file = new FileInputStream(path);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);
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
    }

    public void ComprarAccion (String DNI,String Empresa, int numAcciones, double precioAccion){
        try{
            if(this.clientes.containsKey(DNI)){
                this.clientes.get(DNI).compraPaquete(Empresa, numAcciones, precioAccion);
            } else {
                throw new ExcepcionClientes("Cliente no existe");
            }
        }catch (ExcepcionClientes ex){
            ex.getMessage();
        }

    }

    public void VenderAccion (String DNI, String Empresa, int numAcciones){
        try {
            if (this.clientes.containsKey(DNI)) {
                this.clientes.get(DNI).vendePaquete(Empresa, numAcciones);
            } else {
                throw new ExcepcionClientes("Cliente no existe");
            }
        }catch (ExcepcionClientes ex){
            ex.getMessage();
        }
    }

    public void ActualizarClientes(String[] empresas, Double[] precios){ //Arreglar
        int sizeC = this.clientes.size();
        Collection<Cliente> collectionClientes = this.clientes.values();
        Cliente[] arrayClientes = (Cliente[]) collectionClientes.toArray();
        int sizeE = empresas.length;
        for (int i=0; i< sizeC; i++){
            for (int j=0; j < sizeE; j++){
                arrayClientes[i].actualizarPaquete(empresas[j], precios[j]);
            }
        }
        HashMap<String, Cliente> newCliente = new HashMap<String, Cliente>();
        for (int k=0; k < sizeC; k++){
            newCliente.put(arrayClientes[k].getDNI(), arrayClientes[k]);
        }
        this.clientes = newCliente;
    }
    public void ComprarAcciones(BolsaDeValores bolsa){ // Manda mensaje de compra al broker
        try {
            Escaner escaner = new Escaner();
            System.out.println("Introduzca el DNI del cliente: ");
            String DNI = escaner.leerString();
            if (!clientes.containsKey(DNI)) throw new ExcepcionNoExisteValor();

            System.out.println("Introduzca el nombre de la empresa: ");
            String empresa = escaner.leerString();
            if (!bolsa.existeEmpresa(empresa)) throw new ExcepcionNoExisteValor();

            System.out.println("Introduzca la cantidad máxima a invertir: ");
            double cantidadMax = escaner.leerDouble();
            if (clientes.get(DNI).getSaldo()<cantidadMax) throw new ExcepcionSaldoInsuficiente();

            MensajeCompra operacionCompra = new MensajeCompra(DNI, empresa, cantidadMax);
            agenteInversiones.guardarOperacion(operacionCompra);
            System.out.println("Petición almacenada en la lista de peticiones del bróker.");
        }
        catch (ExcepcionNoExisteValor e){
            System.out.println("El valor introducido no existe.");
        }
        catch (ExcepcionSaldoInsuficiente e){
            System.out.println("El cliente no tiene dinero suficiente para realizar esta operación.");
        }
    }
    public void VenderAcciones(BolsaDeValores bolsa){ // Manda mensaje de venta al broker
        try{
            Escaner escaner = new Escaner();
            System.out.println("Introduzca el DNI del cliente: ");
            String DNI = escaner.leerString();
            if (!clientes.containsKey(DNI)) throw new ExcepcionNoExisteValor();

            System.out.println("Introduzca el nombre de la empresa: ");
            String empresa = escaner.leerString();
            if (!bolsa.existeEmpresa(empresa)) throw new ExcepcionNoExisteValor();

            System.out.println("Introduzca el número de acciones a vender: ");
            int acciones = escaner.leerInt();
            if (acciones<=0) throw new ExcepcionNoNulo();
            if (!clientes.get(DNI).suficientesAcciones(empresa,acciones)) throw new ExcepcionPaquetes();

            MensajeVenta operacionVenta = new MensajeVenta(DNI,empresa,acciones);
            agenteInversiones.guardarOperacion(operacionVenta);
            System.out.println("Petición almacenada en la lista de peticiones del bróker.");
        }
        catch (ExcepcionNoExisteValor ex){
            System.out.println("El valor introducido no existe");
        }
        catch (ExcepcionNoNulo ex){
            System.out.println("Error: no se puede comprar este número de acciones.");
        }
        catch (ExcepcionPaquetes ex){
            System.out.println("Error: El cliente no tiene suficientes acciones.");
        }
    }
    public void ActualizarValoresBanco(){ // Manda mensaje de actualizacion al broker
        MensajeActualizacion operacionActualizacion = new MensajeActualizacion();
        agenteInversiones.guardarOperacion(operacionActualizacion);
        System.out.println("Petición almacenada en la lista de peticiones del bróker.");
    }
    //endregion
}
