package Banco;

import Bolsa.BolsaDeValores;

public class GestorDeInversiones extends Persona {
    //region Atributos
    private BolsaDeValores bolsa;
    //endregion
    //region Constructor
    public GestorDeInversiones(String nombre, String DNI, BolsaDeValores bolsa){
        super(DNI, nombre);
        this.bolsa = bolsa;
    }
    //endregion

    public void setBolsa(BolsaDeValores bolsa) {
        this.bolsa = bolsa;
    }

    public void recomendacionBolsa(){
        String nombreMejorEmpresa = bolsa.empresaMayorIncremento();
        if (nombreMejorEmpresa == null) System.out.println("La bolsa está vacía de empresas."); //Se lanza cuando la bolsa está vacía
        else System.out.println("La mejor empresa en la que invertir es: "+ nombreMejorEmpresa+ "\n");
    }

}