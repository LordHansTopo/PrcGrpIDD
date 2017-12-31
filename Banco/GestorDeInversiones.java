package Banco;

import Bolsa.BolsaDeValores;
import Bolsa.Empresa;

import java.util.Map;

public class GestorDeInversiones extends Persona {
    //region Atributos
    BolsaDeValores bolsa;
    //endregion
    //region Constructor
    public GestorDeInversiones(String nombre, String DNI, BolsaDeValores bolsa){
        super(nombre, DNI);
        this.bolsa = bolsa;
    }
    //endregion


    public void recomendacionBolsa(){
        String nombreMejorEmpresa = bolsa.empresaMayorIncremento();
        System.out.println("La mejor empresa en la que invertir es: "+ nombreMejorEmpresa+ "/n");
    }

}