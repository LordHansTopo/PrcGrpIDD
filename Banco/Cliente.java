package Banco;


import Excepciones.ExcepcionPaquetes;

import java.util.HashMap;

public class Cliente extends Persona {
    //region Atributos
    private Double saldo;
    private HashMap<String, PaqueteDeAcciones> paqueteDelCliente;

    //endregion
    //region Constructor
    public Cliente(String nombre, String DNI, Double saldo){
        super(nombre, DNI);
        setSaldo(saldo);
        paqueteDelCliente = new HashMap<String, PaqueteDeAcciones>();
    }

    //endregion
    //region Getters y Setters

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    //endregion
    //region Otros metodos
    public void printSaldo(){
        System.out.print(getSaldo());
    }

    public void compraPaquete(String empresa, int numTitulos, Double precioIndiv){
        if(!this.paqueteDelCliente.containsKey(empresa)){
            PaqueteDeAcciones paquete = new PaqueteDeAcciones(empresa, numTitulos, precioIndiv);
            this.paqueteDelCliente.put(empresa,paquete);
        }else{
            int numTitulosTotal = (this.paqueteDelCliente.get(empresa).getNumeroTitulos() + numTitulos);
            System.out.println("El cliente ha obtenido un beneficio de: "+ this.paqueteDelCliente.get(empresa).getVariacion() +" €"); //cambiar tmbn seguramente
            PaqueteDeAcciones paquete = new PaqueteDeAcciones(empresa, numTitulosTotal, precioIndiv);
            this.paqueteDelCliente.put(empresa, paquete);           //Al ser HashMap y tener una Key que ya existia se actualizan los valores del Object
        }
    }

    public void vendePaquete(String empresa, int numTitulosVendidos) throws ExcepcionPaquetes {
        if(!this.paqueteDelCliente.containsKey(empresa)){
            throw new ExcepcionPaquetes("Error, cliente no dispone de acciones de esta empresa"); //esto abria que cambiarlo seguro
        }else{if( numTitulosVendidos > this.paqueteDelCliente.get(empresa).getNumeroTitulos()){
            throw new ExcepcionPaquetes("Error, el cleinte no dispone de tantos paquetes"); //esto abria que cambiarlo seguro
        }else{
            Double precioActual = 1.0; //llama a precio actual de 1 paquete
            int paquetesActuales = (this.paqueteDelCliente.get(empresa).getNumeroTitulos() - numTitulosVendidos);
            if(this.paqueteDelCliente.get(empresa).getNumeroTitulos() == numTitulosVendidos){
                this.paqueteDelCliente.remove(empresa);
            }else{
                this.paqueteDelCliente.get(empresa).setNumeroTitulos(paquetesActuales);
            }
            Double dineroGanado = (numTitulosVendidos * precioActual);
            this.saldo = this.saldo + dineroGanado;
        }
        }
    }

    //endregion
}
