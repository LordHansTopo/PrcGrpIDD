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



public class BancoDeInversores implements Serializable{
    //region Atributos
    private String nombre;
    private AgenteInversiones agenteInversiones;
    private ArrayList<GestorDeInversiones> gestores;
    private HashMap<String, Cliente> clientes;
    private HashMap<String, ClientePremium> clientesPremium;

    //endregion
    //region Constructor
    public BancoDeInversores(String nombre){
        setNombre(nombre);
        setAgenteInversiones(getAgenteInversiones()); //revisar
        gestores = new ArrayList<GestorDeInversiones>();
        clientes = new HashMap<String, Cliente>();
        clientesPremium = new HashMap<String, ClientePremium>();
    }
    //endregion
    //region Getters y Setters
    public void setAgenteInversiones(AgenteInversiones agenteInversiones) {
        this.agenteInversiones = agenteInversiones;
    }

    public AgenteInversiones getAgenteInversiones() {
        return agenteInversiones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
                        System.out.println("Introduzca el saldo del cliente");
                        Double saldo = escaner.leerDouble();

                        Cliente cliente = new Cliente(nombre, DNI, saldo);
                        this.clientes.put(DNI, cliente);
                        System.out.println("Cliente introducido correctamente");
                    } else {
                        throw new ExcepcionClienteNoPerteneceBanco("Cliente ya pertenece");
                    }
                } catch (ExcepcionClienteNoPerteneceBanco ex2) {
                    ex2.getMessage();
                }
            } else {
                throw new ExcepcionClientes("Valor de DNI no valido");
            }
        }catch (ExcepcionClientes ex1){
            ex1.getMessage();
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
                        eliminarClientePremium(DNI);
                        this.clientes.remove(DNI);
                        System.out.println("Cliente con DNI: " + DNI + " Ha sido correctamente eliminado");
                    } else {
                        throw new ExcepcionClienteNoPerteneceBanco("Cliente no pertenece al banco");
                    }
                } catch (ExcepcionClienteNoPerteneceBanco ex2) {
                    ex2.getMessage();
                }
            } else {
                throw new ExcepcionClientes("Valor de DNI no valido");
            }
        }catch (ExcepcionClientes ex1){
            ex1.getMessage();
        }
    }

    private void eliminarClientePremium(String DNI){
        if (this.clientesPremium.containsKey(DNI)){
            this.clientesPremium.remove(DNI);
        }
    }

    public void imprimirClientes(){
        int size = this.clientes.size();
        Collection<Cliente> collectionClientes = this.clientes.values();
        Cliente[] arrayClientes = (Cliente[]) collectionClientes.toArray();
        for (int i=0; i< size; i++){
            System.out.print("DNI: ");
            arrayClientes[i].printDNI();
            System.out.print(" Nombre: ");
            arrayClientes[i].printNombre();
            System.out.print(" Saldo: ");
            arrayClientes[i].printSaldo();
            System.out.println("");
        }
    }

    public void mejorarCliente(){
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();
        Boolean validar = Utilidades.validarDNI(DNI);
        try {
            if (validar) {
                try {
                    if (this.clientes.containsKey(DNI)) {
                        try {
                            if (!this.clientesPremium.containsKey(DNI)) {
                                int aleatorio = (Utilidades.GenerarIntAleat(1, this.gestores.size()) - 1);
                                ClientePremium cliente = new ClientePremium(this.clientes.get(DNI), this.gestores.get(aleatorio));
                                this.clientesPremium.put(cliente.getDNI(), cliente);
                            } else {
                                throw new ExcepcionClienteNoPerteneceBanco("El cliente ya es premium");
                            }
                        } catch (ExcepcionClienteNoPerteneceBanco ex3) {
                            ex3.getMessage();
                        }
                    } else {
                        throw new ExcepcionClienteNoPerteneceBanco("Error, cliente no pertenece al banco");
                    }
                } catch (ExcepcionClienteNoPerteneceBanco ex2){
                    ex2.printStackTrace();
                }
            } else {
                throw new ExcepcionClientes("Valor de DNI no valido");
            }
        }catch(ExcepcionClientes ex1){
            ex1.getMessage();
        }
    }

    public void desmejorarCliente(){   //No se utiliza ni pide en ningun momento, pero lo añado
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();
        try{
            if (this.clientes.containsKey(DNI) == true){
                try{
                    if(this.clientesPremium.containsKey(DNI)) {
                        this.clientesPremium.remove(DNI);
                    }else{
                        throw new ExcepcionClientes("Cliente no es premium");
                    }
                }catch (ExcepcionClientes ex){
                    ex.getMessage();
                }
            }else{
                throw new ExcepcionClientes("Cliente no existe");
            }
        }catch (ExcepcionClientes ex){
            ex.getMessage();
        }
    }

    public void guardarCopiaSeguridad(){
        System.out.println("Introduzca el destino de guardado de la copia");
        Escaner escaner = new Escaner();
        String path = escaner.leerString();
        try{
            FileOutputStream fileOutput = new FileOutputStream(path);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);
            ObjectOutputStream objectOutput = new ObjectOutputStream(bufferedOutput);
            objectOutput.writeObject(this);
            objectOutput.close();

        } catch(Exception ex){
            System.out.println("Error, camino incorrecto");
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
        }
    }

    public void cargarCopiaSeguridad(){
        System.out.println("Introduzca el destino de carga de la copia");
        Escaner escaner = new Escaner();
        String path = escaner.leerString();
        try{
            FileInputStream fileInput = new FileInputStream(path);
            BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);
            ObjectInputStream objectInput = new ObjectInputStream(bufferedInput);
            BancoDeInversores banco = (BancoDeInversores) objectInput.readObject();
            objectInput.close();

        } catch(Exception ex){
            System.out.println("Error, camino incorrecto");
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
        }
    }

    public void ComprarAccion (String DNI,String Empresa, int numAcciones, double precioAccion, double cantRestante) throws ExcepcionClientes{
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

    public void VenderAccion (String DNI, String Empresa, int numAcciones, double ganancias) throws ExcepcionPaquetes {
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

    public void ActualizarClientes(String[] empresas, Double[] precios){
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
    public void ComprarAcciones(BolsaDeValores bolsa,AgenteInversiones agente){
        try {
            Escaner escaner = new Escaner();
            System.out.println("Introduzca el DNI del cliente: ");
            String DNI = escaner.leerString();
            if (!clientes.containsKey(DNI) && !clientesPremium.containsKey(DNI)) throw new ExcepcionNoExisteValor();

            System.out.println("Introduzca el nombre de la empresa: ");
            String empresa = escaner.leerString();
            if (!bolsa.existeEmpresa(empresa)) throw new ExcepcionNoExisteValor();

            System.out.println("Introduzca la cantidad máxima a invertir: ");
            double cantidadMax = escaner.leerDouble();
            if (clientes.get(DNI).getSaldo()<cantidadMax) throw new ExcepcionSaldoInsuficiente();

            MensajeCompra operacionCompra = new MensajeCompra(DNI, empresa, cantidadMax);
            agente.guardarOperacion(operacionCompra);
            System.out.println("Petición almacenada en la lista de peticiones del bróker.");
        }
        catch (ExcepcionNoExisteValor e){
            System.out.println("El valor introducido no existe.");
        }
        catch (ExcepcionSaldoInsuficiente e){
            System.out.println("El cliente no tiene dinero suficiente para realizar esta operación.");
        }
    }
    public void VenderAcciones(BolsaDeValores bolsa, AgenteInversiones agente){ //WIP
        try{
            Escaner escaner = new Escaner();
            System.out.println("Introduzca el DNI del cliente: ");
            String DNI = escaner.leerString();
            if (!clientes.containsKey(DNI) && !clientesPremium.containsKey(DNI)) throw new ExcepcionNoExisteValor();

            System.out.println("Introduzca el nombre de la empresa: ");
            String empresa = escaner.leerString();
            if (!bolsa.existeEmpresa(empresa)) throw new ExcepcionNoExisteValor();

            System.out.println("Introduzca el número de acciones a vender: ");
            int acciones = escaner.leerInt();
            if (acciones<=0) throw new ExcepcionNoNulo();

            MensajeVenta operacionVenta = new MensajeVenta(DNI,empresa,acciones);
            agente.guardarOperacion(operacionVenta);
            System.out.println("Petición almacenada en la lista de peticiones del bróker.");
        }
        catch (ExcepcionNoExisteValor ex){
            System.out.println("El valor introducido no existe");
        }
        catch (ExcepcionNoNulo ex){
            System.out.println("Error: no se puede comprar este número de acciones.");
        }
    }
    public void ActualizarValoresBanco(AgenteInversiones agente){
        MensajeActualizacion operacionActualizacion = new MensajeActualizacion();
        agente.guardarOperacion(operacionActualizacion);
        System.out.println("Petición almacenada en la lista de peticiones del bróker.");
    }
    //endregion
}
