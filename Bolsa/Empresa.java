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

    public double getIncremento() {
        return incremento;
    }

    public Empresa(String nombre,double minimo, double maximo){
        this.nombre=nombre;
        this.valor= Utilidades.GenerarNumAleat(minimo,maximo);
        this.valorAnt=0;
        this.incremento=valor;
    }

    public void imprimirInfo(){
        System.out.println("Nombre: " + nombre);
        DecimalFormat formateadorValores = new DecimalFormat("0.00");
        System.out.println("Valor actual de acción: " + formateadorValores.format(valor)+ "€" );
        System.out.println("Valor anterior de acción: " + formateadorValores.format(valorAnt)+ "€" );
        System.out.println("Incremento: " + formateadorValores.format(incremento) + "€" + "\n");
    }
    public void actualizarValoresEmpresa(double nuevoValor){
        valorAnt=valor;
        valor=nuevoValor;
        incremento=valor-valorAnt;
    }
}
