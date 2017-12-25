package Bolsa;

import General.Utilidades;
import java.io.Serializable;
import java.text.DecimalFormat;

public class Empresa implements Serializable{
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
    public boolean equals(Object o1){ //Dos empresas son iguales si tienen el mismo nombre
        return (this.nombre.equals(((Empresa)o1).getNombre()));
    }
    public void imprimirInfo(){
        System.out.println("Nombre: " + nombre);
        DecimalFormat formateadorValores = new DecimalFormat("0.00");
        System.out.println("Valor actual de acción: " + formateadorValores.format(valor)+ "€" );
        System.out.println("Valor anterior de acción: " + formateadorValores.format(valorAnt)+ "€" );
        System.out.println("Incremento: " + formateadorValores.format(incremento) + "€" + "\n");
    }
    public void actualizarValoresEmpresa(){
        valorAnt=valor;
        valor=Utilidades.GenerarNumAleat(20000);
        incremento=valor-valorAnt;
    }
}
