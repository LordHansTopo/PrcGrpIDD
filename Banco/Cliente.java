package Banco;


import java.util.HashMap;

public class Cliente extends Persona {
    private Double saldo;
    private HashMap<String, PaqueteDeAcciones> paqueteDelCliente;


    public Cliente(String nombre, String DNI, Double saldo){
        super(nombre, DNI);
        setSaldo(saldo);
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void printSaldo(){
        System.out.print(getSaldo());
    }

}
