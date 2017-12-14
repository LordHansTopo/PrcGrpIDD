

package Banco;

import General.Escaner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.io.*;



public class Banco {
    private String nombre;
    private AgenteInversiones agenteInversiones;
    private HashMap<String, GestorDeInversiones> gestores;
    private HashMap<String, Cliente> clientes;
    private HashMap<String, ClientePremium> clientesPremium;

    //Constructor
    public Banco(String nombre){
        setNombre(nombre);
        setAgenteInversiones(getAgenteInversiones()); //revisar
        gestores = new HashMap<String, GestorDeInversiones>();
        clientes = new HashMap<String, Cliente>();
        clientesPremium = new HashMap<String, ClientePremium>();
    }

    //Getters y Setters
    public void setAgenteInversiones(AgenteInversiones agenteInversiones) {
        this.agenteInversiones = agenteInversiones;
    }

    public AgenteInversiones getAgenteInversiones() {
        return agenteInversiones;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    public HashMap<String, ClientePremium> getClientesPremium() {
        return clientesPremium;
    }

    public void setClientesPremium(HashMap<String, ClientePremium> clientesPremium) {
        this.clientesPremium = clientesPremium;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<String, GestorDeInversiones> getGestores() {
        return gestores;
    }

    public void setGestores(HashMap<String, GestorDeInversiones> gestores) {
        this.gestores = gestores;
    }

    //Otros metodos

    public void insertarCliente() throws IOException {
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();

        if(!getClientes().containsKey(DNI)){
            System.out.println("Introduzca nombre del cliente");
            String nombre = escaner.leerString();
            System.out.println("Introduzca el saldo del cliente");
            Double saldo = escaner.leerDouble();

            Cliente cliente = new Cliente(nombre,DNI,saldo);
            getClientes().put(DNI,cliente);
            System.out.println("Cliente introducido correctamente");
        }else{
            System.out.println("Cliente ya pertenece");
        }
    }

    public void eliminarCliente(){
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();

        if (getClientesPremium().containsKey(DNI)) {
            eliminarClientePremium(DNI);
        }
        if (getClientes().containsKey(DNI)){
            getClientes().remove(DNI);
            System.out.println("Cliente con DNI: "+ DNI + " Ha sido correctamente eliminado");
        }else {
            System.out.println("Cliente no pertenece al banco");
        }
    }

    public void eliminarClientePremium(String DNI){
        if (getClientesPremium().containsKey(DNI)){
            getClientesPremium().remove(DNI);
        }
    }

    public void imprimirClientes(){
        int size = getClientes().size();
        Collection<Cliente> collectionClientes = getClientes().values();
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

    public void copiaSeguridad(String path) {
        /*try(FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);){
            objectOutputStream.writeObject(this.cartera);
        } catch(Exception ex){
            System.out.println("Error serializando");
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
        }*/
    }


    public void mejorarCliente(){
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();

        if (getClientes().containsKey(DNI) == true){
            if (getClientesPremium().containsKey(DNI) == false){
                System.out.println("Introduzca DNI del gestor");
                String DNIgestor = escaner.leerString();
                if(getGestores().containsKey(DNIgestor)) {
                    ClientePremium cliente = new ClientePremium(getClientes().get(DNI), getGestores().get(DNIgestor));
                    getClientesPremium().put(cliente.getDNI(), cliente);
                }else{
                    System.out.println("DNI del gestor incorrecto");
                }
            }else{
                System.out.println("El cliente ya es premium");
            }
        }else{
            System.out.println("Error, cliente no pertenece al banco");
        }
    }

    public void desmejorarCliente(){
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();

        if (getClientes().containsKey(DNI)){
            if(getClientesPremium().containsKey(DNI)) {
                getClientesPremium().remove(DNI);
            }else{
                System.out.println("Cliente no era premium");
            }
        }else{
            System.out.println("Cliente no existe");
        }

    }


}
