package Banco;

import java.io.Serializable;

public class PaqueteDeAcciones implements Serializable{
    //region Atributos
    private String nombreEmpresa;
    private int numeroTitulos;
    private double precioIndividual;

    //Atributos derivados
    private double precioCompleto;
    private double precioOriginal;
    private double variacion;

    //endregion
    //region Constructor
    public PaqueteDeAcciones(String nombreEmpresa, Integer numeroTitulos, double precioIndividual) {
        this.nombreEmpresa = nombreEmpresa;
        this.numeroTitulos = numeroTitulos;
        this.precioIndividual = precioIndividual;
        this.precioCompleto = (numeroTitulos * precioIndividual);
        this.precioOriginal = precioIndividual;
        this.variacion = 0.0;

    }

    //endregion
    //region Getters y Setters

    public Integer getNumeroTitulos() {
        return numeroTitulos;
    }

    public void setNumeroTitulos(Integer numeroTitulos) {
        this.numeroTitulos = numeroTitulos;
    }

    private Double getPrecioIndividual() {
        return precioIndividual;
    }

    private void setPrecioIndividual(double precioIndividual) {
        this.precioIndividual = precioIndividual;
    }

    private Double getPrecioCompleto() {
        return precioCompleto;
    }

    private void setPrecioCompleto() {
        this.precioCompleto = (getNumeroTitulos() * getPrecioIndividual());
    }

    private Double getPrecioOriginal() {
        return precioOriginal;
    }

    private void setVariacion() {
        this.variacion = (getPrecioCompleto() - getPrecioOriginal());
    }

    //endregion
    //region Otros metodos
    public void actualizarValores(Double precioActual){
        //actualiza el precio de 1 accion
        setPrecioIndividual(precioActual);
        setPrecioCompleto();
        setVariacion();
    }

    //endregion
}