package Banco;

import Excepciones.ExcepcionClientes;
import General.Escaner;
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
    public void insertarCliente() throws ExcepcionClientes{
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();
        Boolean validar = new General.Utilidades().validarDNI(DNI);
        if(validar) {
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
            } catch (ExcepcionClientes ex) {
                ex.getMessage();
            }
        } else {
            throw new ExcepcionClientes("Valor de DNI no valido");
        }
    }

    public void eliminarCliente()throws ExcepcionClientes{
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();
        Boolean validar = new General.Utilidades().validarDNI(DNI);
        if(validar) {
            try{
                if (this.clientes.containsKey(DNI)){
                    eliminarClientePremium(DNI);
                    this.clientes.remove(DNI);
                    System.out.println("Cliente con DNI: "+ DNI + " Ha sido correctamente eliminado");
                }else {
                    throw new ExcepcionClientes("Cliente no pertenece al banco");
                }
            }catch(ExcepcionClientes ex){
                ex.getMessage();
            }
        } else {
            throw new ExcepcionClientes("Valor de DNI no valido");
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

    public void mejorarCliente() throws ExcepcionClientes {
        System.out.println("Introduzca DNI del cliente");
        Escaner escaner = new Escaner();
        String DNI = escaner.leerString();
        Boolean validar = new General.Utilidades().validarDNI(DNI);
        if(validar) {
            if (this.clientes.containsKey(DNI) == true){
                try{
                    if (this.clientesPremium.containsKey(DNI) == false){
                        int aleatorio = (new General.Utilidades().GenerarIntAleat(1, this.gestores.size())-1);
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
        } else {
            throw new ExcepcionClientes("Valor de DNI no valido");
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
    //endregion
}
