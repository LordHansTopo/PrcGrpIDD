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
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Integer getNumeroTitulos() {
        return numeroTitulos;
    }

    public void setNumeroTitulos(Integer numeroTitulos) {
        this.numeroTitulos = numeroTitulos;
    }

    public Double getPrecioIndividual() {
        return precioIndividual;
    }

    public void setPrecioIndividual(double precioIndividual) {
        this.precioIndividual = precioIndividual;
    }

    public Double getPrecioCompleto() {
        return precioCompleto;
    }

    public void setPrecioCompleto() {
        this.precioCompleto = (getNumeroTitulos() * getPrecioIndividual());
    }

    public Double getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(double precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public Double getVariacion() {
        return variacion;
    }

    public void setVariacion() {
        this.variacion = (getPrecioCompleto() - getPrecioOriginal());
    }

    //endregion
    //region Otros metodos
    public void actualizarValores(Double precioActual){
        setPrecioIndividual(precioActual);
        setPrecioCompleto();
        setVariacion();
    }

    //endregion
}