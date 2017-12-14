package Banco;


import java.util.HashMap;

public class Cliente extends Persona {
    //Atributos
    private Double saldo;
    private HashMap<String, PaqueteDeAcciones> paqueteDelCliente;

    //Endregion
    //Constructor
    public Cliente(String nombre, String DNI, Double saldo){
        super(nombre, DNI);
        setSaldo(saldo);
        paqueteDelCliente = new HashMap<String, PaqueteDeAcciones>();
    }

    //Endregion
    //Getters y Setters

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    //Endregion
    //Otros metodos
    public void printSaldo(){
        System.out.print(getSaldo());
    }

    public void compraPaquete(String empresa, int numTitulos, Double precioIndiv){
        if(!this.paqueteDelCliente.containsKey(empresa)){
            PaqueteDeAcciones paquete = new PaqueteDeAcciones(empresa, numTitulos, precioIndiv);
            this.paqueteDelCliente.put(empresa,paquete);
        }else{
            int numTitulosTotal = (this.paqueteDelCliente.get(empresa).getNumeroTitulos() + numTitulos);
            System.out.println("El cliente ha obtenido un beneficio de: "+ this.paqueteDelCliente.get(empresa).getVariacion() +" €");
            PaqueteDeAcciones paquete = new PaqueteDeAcciones(empresa, numTitulosTotal, precioIndiv);
            this.paqueteDelCliente.put(empresa, paquete);           //Al ser HashMap y tener una Key que ya existia se actualizan los valores del Object
        }
    }

    //Endregion
}
