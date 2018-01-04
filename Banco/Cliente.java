package Banco;


import Excepciones.ExcepcionPaquetes;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Cliente extends Persona {
    //region Atributos
    private double saldo;
    private HashMap<String, PaqueteDeAcciones> paqueteDelCliente;

    //endregion
    //region Constructor
    public Cliente(String nombre, String DNI, double Saldo){
        super(nombre, DNI);
        saldo=Saldo;
        paqueteDelCliente = new HashMap<String, PaqueteDeAcciones>();
    }

    //endregion
    //region Getters y Setters

    public double getSaldo() {
        return saldo;
    }

    //endregion
    //region Otros metodos

    public void compraPaquete(String empresa, int numTitulos, double precioIndiv){
        if(!this.paqueteDelCliente.containsKey(empresa)){
            // si el cliente no tiene acciones de la empresa
            PaqueteDeAcciones paquete = new PaqueteDeAcciones(empresa, numTitulos, precioIndiv);
            this.paqueteDelCliente.put(empresa,paquete);
        }else{
            //si el cliente si tiene acciones de la empresa
            int numTitulosTotal = (this.paqueteDelCliente.get(empresa).getNumeroTitulos() + numTitulos);
            PaqueteDeAcciones paquete = new PaqueteDeAcciones(empresa, numTitulosTotal, precioIndiv);
            this.paqueteDelCliente.put(empresa, paquete); //Al ser HashMap y tener una Key que ya existia se actualizan los valores del Object
        }
        saldo-=precioIndiv*numTitulos;
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
                        throw new ExcepcionPaquetes("Error, el cliente no dispone de tantas acciones.");
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
                        this.saldo += dineroGanado;
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
        return (paqueteDelCliente.containsKey(empresa)) && (this.paqueteDelCliente.get(empresa).getNumeroTitulos()>=numAcciones);
    }
    public void imprimirAcciones(){
        if (this.paqueteDelCliente.isEmpty()){
            System.out.println("El cliente no tiene acciones.");
        }
        else{
            DecimalFormat formateadorValores = new DecimalFormat("0.00");
            System.out.println("Acciones:");
            for (Map.Entry<String, PaqueteDeAcciones> cliente : paqueteDelCliente.entrySet()){
                System.out.println("Empresa: " + cliente.getKey());
                System.out.println("Número de acciones: " + cliente.getValue().getNumeroTitulos());
                System.out.println("Precio por accion: " + formateadorValores.format(cliente.getValue().getPrecioIndividual()) + " €");
                System.out.println("Precio completo: " + formateadorValores.format(cliente.getValue().getPrecioCompleto()) + " €");
                System.out.println("Precio original de compra de cada acción: " +
                        formateadorValores.format(cliente.getValue().getPrecioOriginal()) + " €");
                System.out.println("Variación del precio original: " + formateadorValores.format(cliente.getValue().getVariacion()) + " €\n");
            }
        }
    }
    //endregion
}
