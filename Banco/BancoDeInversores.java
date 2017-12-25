package Banco;

import Bolsa.BolsaDeValores;
import Excepciones.ExcepcionClientes;
import Excepciones.ExcepcionNoExisteValor;
import Excepciones.ExcepcionSaldoInsuficiente;
import General.Escaner;
import Mensajes.MensajeCompra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.io.*;



public class BancoDeInversores {
    //Atributos
    private String nombre;
    private AgenteInversiones agenteInversiones;
    private ArrayList<GestorDeInversiones> gestores;
    private HashMap<String, Cliente> clientes;
    private HashMap<String, ClientePremium> clientesPremium;

    //Endregion
    //Constructor
    public BancoDeInversores(String nombre){
        setNombre(nombre);
        setAgenteInversiones(getAgenteInversiones()); //revisar
        gestores = new ArrayList<GestorDeInversiones>();
        clientes = new HashMap<String, Cliente>();
        clientesPremium = new HashMap<String, ClientePremium>();
    }

    //Endregion
    //Getters y Setters
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



    //Endregion
    //Otros metodos
    public void insertarCliente(){
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();
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
                throw new ExcepcionClientes("Cliente ya pertenece");
            }
        }catch(ExcepcionClientes ex){
            ex.getMessage();
        }
    }


    public void eliminarCliente(){
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();
        try{
            if (this.clientesPremium.containsKey(DNI)) {
                eliminarClientePremium(DNI);
            }
            if (this.clientes.containsKey(DNI)){
                this.clientes.remove(DNI);
                System.out.println("Cliente con DNI: "+ DNI + " Ha sido correctamente eliminado");
            }else {
                throw new ExcepcionClientes("Cliente no pertenece al banco");
            }
        }catch(ExcepcionClientes ex){
            ex.getMessage();
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
        try{
            if (this.clientes.containsKey(DNI) == true){
                try{
                    if (this.clientesPremium.containsKey(DNI) == false){
                        int aleatorio = (new General.Utilidades().GenerarIntAleat(1, this.gestores.size()))-1;

                        ClientePremium cliente = new ClientePremium(this.clientes.get(DNI), this.gestores.get(aleatorio));
                        this.clientesPremium.put(cliente.getDNI(), cliente);
                    }else{
                        throw new ExcepcionClientes("El cliente ya es premium");
                    }
                }catch (ExcepcionClientes ex){
                    ex.getMessage();
                }
            }else{
                throw new ExcepcionClientes("Error, cliente no pertenece al banco");
            }
        }catch (ExcepcionClientes ex){
            ex.getMessage();
        }
    }

    public void desmejorarCliente(){   //No se utiliza ni pide en ningun momento, pero lo añado (por que me sale de los huevos)?
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();
        try{
            if (this.clientes.containsKey(DNI) == true){
                try{
                    if(this.clientesPremium.containsKey(DNI)) {
                        this.clientesPremium.remove(DNI);
                    }else{
                        throw new ExcepcionClientes("Cliente ya es premium");
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

    public void copiaSeguridad(){
        System.out.println("Introduzca el destino de guardado de la copia");
        Escaner escaner = new Escaner();
        String path = escaner.leerString();
        try{
            FileOutputStream fileOutput = new FileOutputStream(path);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(this.clientes);
            objectOutput.close();


            path = path + "Premium";

            FileOutputStream fileOutputPremium = new FileOutputStream(path);
            ObjectOutputStream objectOutputPremium = new ObjectOutputStream(fileOutputPremium);
            objectOutput.writeObject(this.clientesPremium);
            objectOutput.close();

        } catch(Exception ex){
            System.out.println("Error, camino incorrecto");
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
        }
    }
    public void ComprarAcciones(BolsaDeValores bolsa,AgenteInversiones agente){
        try {
            Escaner escaner = new Escaner();
            System.out.println("Introduzca el DNI del cliente: ");
            String DNI = escaner.leerString();
            if (!clientes.containsKey(DNI)) throw new ExcepcionNoExisteValor();

            String nombreCliente = clientes.get(DNI).getNombre();
            System.out.println("Introduzca el nombre de la empresa: ");
            String empresa = escaner.leerString();
            if (!bolsa.existeEmpresa(empresa)) throw new ExcepcionNoExisteValor();

            System.out.println("Introduzca la cantidad máxima a invertir: ");
            double cantidadMax = escaner.leerDouble();
            if (clientes.get(DNI).getSaldo()<cantidadMax) throw new ExcepcionSaldoInsuficiente();

            MensajeCompra operacionCompra = new MensajeCompra(nombreCliente, empresa, cantidadMax);
            agente.guardarOperacion(operacionCompra);
            System.out.println("Petición almacenada en la lista de peticiones del bróker.");
        }
        catch (ExcepcionNoExisteValor e){
            System.out.println("El valor introducido no existe");
        }
        catch (ExcepcionSaldoInsuficiente e){
            System.out.println("El cliente no tiene dinero suficiente para realizar esta operación.");
        }
    }
    //Endregion
}
