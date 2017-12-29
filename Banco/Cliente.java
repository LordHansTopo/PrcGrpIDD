package Banco;


import Excepciones.ExcepcionPaquetes;

import java.util.HashMap;

public class Cliente extends Persona {
    //Atributos
    private double saldo;
    private HashMap<String, PaqueteDeAcciones> paqueteDelCliente;

    //Endregion
    //Constructor
    public Cliente(String nombre, String DNI, double saldo){
        super(nombre, DNI);
        setSaldo(saldo);
        paqueteDelCliente = new HashMap<String, PaqueteDeAcciones>();
    }

    //Endregion
    //Getters y Setters

    public double getSaldo() {
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

    public void compraPaquete(String empresa, int numTitulos, double precioIndiv){
        if(!this.paqueteDelCliente.containsKey(empresa)){
            PaqueteDeAcciones paquete = new PaqueteDeAcciones(empresa, numTitulos, precioIndiv);
            this.paqueteDelCliente.put(empresa,paquete);
        }else{
            int numTitulosTotal = (this.paqueteDelCliente.get(empresa).getNumeroTitulos() + numTitulos);
            //System.out.println("El cliente ha obtenido un beneficio de: "+ this.paqueteDelCliente.get(empresa).getVariacion() +" €");
            PaqueteDeAcciones paquete = new PaqueteDeAcciones(empresa, numTitulosTotal, precioIndiv);
            this.paqueteDelCliente.put(empresa, paquete);           //Al ser HashMap y tener una Key que ya existia se actualizan los valores del Object
        }
    }

    public void vendePaquete(String empresa, int numTitulosVendidos){
        try {
            if (!this.paqueteDelCliente.containsKey(empresa)) {
                throw new ExcepcionPaquetes("Error, cliente no dispone de acciones de esta empresa"); //esto habria que cambiarlo seguro
            } else {
                try {
                    if (numTitulosVendidos > this.paqueteDelCliente.get(empresa).getNumeroTitulos()) {
                        throw new ExcepcionPaquetes("Error, el cleinte no dispone de tantos paquetes"); //esto habria que cambiarlo seguro
                    } else{
                        double precioActual = 1.0; //llama a precio actual de 1 paquete
                        int paquetesActuales = (this.paqueteDelCliente.get(empresa).getNumeroTitulos() - numTitulosVendidos);
                        if (this.paqueteDelCliente.get(empresa).getNumeroTitulos() == numTitulosVendidos) {
                            this.paqueteDelCliente.remove(empresa);
                        } else {
                            this.paqueteDelCliente.get(empresa).setNumeroTitulos(paquetesActuales);
                        }
                        double dineroGanado = (numTitulosVendidos * precioActual);
                        this.saldo = this.saldo + dineroGanado;
                    }
                }catch (ExcepcionPaquetes ex2) {
                    ex2.getMessage();
                }
            }
        } catch (ExcepcionPaquetes ex1) {
            ex1.getMessage();
        }
    }

    public boolean containsEmpresa(String empresa){
        if(this.paqueteDelCliente.containsKey(empresa)){
            return true;
        }else{
            return false;
        }
    }

    public void actualizarPaquete(String empresa, Double precioA){
        if (this.paqueteDelCliente.containsKey(empresa)){
            this.paqueteDelCliente.get(empresa).actualizarValores(precioA);
        }
    }

    public boolean suficientesAcciones(String empresa, int numAcciones){
        return (this.paqueteDelCliente.get(empresa).getNumeroTitulos()>=numAcciones);
    }
    //Endregion
}
