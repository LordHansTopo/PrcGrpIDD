package Bolsa;

import General.Utilidades;

public class Empresa {
    private String nombre;
    private double valor;
    private double valorAnt;
    private double incremento;

    public String getNombre() {
        return nombre;
    }

    public double getValor() {
        return valor;
    }

    public double getValorAnt() {
        return valorAnt;
    }

    public double getIncremento() {
        return incremento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setValorAnt(double valorAnt) {
        this.valorAnt = valorAnt;
    }

    public void setIncremento(double incremento) {
        this.incremento = incremento;
    }

    public Empresa(String nombre){
        this.setNombre(nombre);
        this.setValor(Utilidades.GenerarNumAleat(5000));
        this.setValorAnt(0);
        this.setIncremento(0);
    }
}
