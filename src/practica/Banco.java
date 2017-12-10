package practica;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.io.*;



public class Banco {
    private String nombre;
    private AgenteInversiones agenteBolsa;
    private HashMap<String, Cliente> cartera;



    public Banco(String nombre, AgenteInversiones agenteBolsa){
        setNombre(nombre);
        setAgenteBolsa(agenteBolsa);
        this.cartera = new HashMap<String, Cliente>();
    }
    public void insertarCliente(Cliente persona){
        this.cartera.put(persona.getDNI(),persona);
    }

    public HashMap<String, Cliente> eliminarCliente(String DNI){
        if (this.cartera.containsKey(DNI)){
            this.cartera.remove(DNI);
            System.out.println("Cliente con DNI: "+ DNI + " Ha sido correctamente eliminado");
            return this.cartera;
        }else {
            System.out.println("Cliente no pertenece al banco");
            return this.cartera;
        }
    }

    public void imprimirCartera(){
        int size = this.cartera.size();
        Collection<Cliente> copia = this.cartera.values();
        Cliente[] copiaI = (Cliente[]) copia.toArray();
        for (int i=0; i< size; i++){
            copiaI[i].printDNI();
            copiaI[i].printNombre();
            copiaI[i].printSaldo();
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AgenteInversiones getAgenteBolsa() {
        return agenteBolsa;
    }

    public void setAgenteBolsa(AgenteInversiones agenteBolsa) {
        this.agenteBolsa = agenteBolsa;
    }


}
