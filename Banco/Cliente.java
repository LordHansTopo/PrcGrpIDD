package Banco;


import Excepciones.ExcepcionPaquetes;

import java.util.HashMap;

public class Cliente extends Persona {
    //region Atributos
    private double saldo;
    private HashMap<String, PaqueteDeAcciones> paqueteDelCliente;

    //endregion
    //region Constructor
    public Cliente(String nombre, String DNI, double saldo){
        super(nombre, DNI);
        setSaldo(saldo);
        paqueteDelCliente = new HashMap<String, PaqueteDeAcciones>();
    }

    //endregion
    //region Getters y Setters

    public double getSaldo() {
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

    public void compraPaquete(String empresa, int numTitulos, double precioIndiv){
        if(!this.paqueteDelCliente.containsKey(empresa)){
            // si el cliente no tiene acciones de la empresa
            PaqueteDeAcciones paquete = new PaqueteDeAcciones(empresa, numTitulos, precioIndiv);
            this.paqueteDelCliente.put(empresa,paquete);
        }else{
            //si el cliente si tiene acciones de la empresa
            int numTitulosTotal = (this.paqueteDelCliente.get(empresa).getNumeroTitulos() + numTitulos);
            //System.out.println("El cliente ha obtenido un beneficio de: "+ this.paqueteDelCliente.get(empresa).getVariacion() +" €");
            PaqueteDeAcciones paquete = new PaqueteDeAcciones(empresa, numTitulosTotal, precioIndiv);
            this.paqueteDelCliente.put(empresa, paquete);           //Al ser HashMap y tener una Key que ya existia se actualizan los valores del Object
        }
    }

    public void vendePaquete(String empresa, int numTitulosVendidos, double precioAccionActual){
        //funcion de venta del acciones de una empresa
        try {
            if (!this.paqueteDelCliente.containsKey(empresa)) {
                throw new ExcepcionPaquetes("Error, cliente no dispone de acciones de esta empresa");
            } else {
                try {
                    if (numTitulosVendidos > this.paqueteDelCliente.get(empresa).getNumeroTitulos()) {
                        //excepcion si se intenta vender mas acciones de las que se posee
                        throw new ExcepcionPaquetes("Error, el cleinte no dispone de tantos paquetes");
                    } else{
                        int paquetesActuales = (this.paqueteDelCliente.get(empresa).getNumeroTitulos() - numTitulosVendidos);
                        if (this.paqueteDelCliente.get(empresa).getNumeroTitulos() == numTitulosVendidos) {
                            //opcion si se venden todas las acciones que se poseen de la empresa
                            this.paqueteDelCliente.remove(empresa);
                        } else {
                            //opcion si se venden menos titulos de los totales
                            this.paqueteDelCliente.get(empresa).setNumeroTitulos(paquetesActuales);
                        }
                        double dineroGanado = (numTitulosVendidos * precioAccionActual); //actualizacion del saldo del cliente tras la venta
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

    public void actualizarPaquete(String empresa, Double precioA){
        //si el cliente tiene acciones de la empresa se llama a actualizarValores
        if (this.paqueteDelCliente.containsKey(empresa)){
            this.paqueteDelCliente.get(empresa).actualizarValores(precioA);
        }
    }

    public boolean suficientesAcciones(String empresa, int numAcciones){
        //devuelve true si se dispone de tienen mas o igual numero de acciones que las disponibles
        return (this.paqueteDelCliente.get(empresa).getNumeroTitulos()>=numAcciones);
    }
    //endregion
}
