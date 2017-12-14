package Bolsa;

import General.Escaner;
import General.Utilidades;

import java.util.HashSet;

public class BolsaDeValores {
    private HashSet<Empresa> bolsa;

    public void AñadirEmpresa(){
        Escaner escaner = new Escaner();
        System.out.println("Introduzca el nombre de la empresa a añadir:");
        String nombre = escaner.leerString();
        if (this.bolsa.add(new Empresa(nombre))) System.out.println("Empresa introducida con éxito.");
        else System.out.println("Error: La empresa ya existe.");
    }
    public void EliminarEmpresa(){
        Escaner escaner = new Escaner();
        System.out.println("Introduzca el nombre de la empresa a eliminar:");
        String eliminarNombre = escaner.leerString();
        boolean eliminada=false;
        if (!this.bolsa.isEmpty()) {
            for (Empresa empresa : this.bolsa) {
                if (empresa.getNombre().equalsIgnoreCase(eliminarNombre)){
                    eliminada = this.bolsa.remove(empresa);
                    break;
                }
            }
            if (eliminada){
                System.out.println("Empresa eliminada con éxito.");
            }
            else{
                System.out.println("No se ha encontrado la empresa en la bolsa.");
            }
        }
        else{
            System.out.println("La bolsa no contiene empresas.");
        }
    }
    public void EstadoBolsa(){
        if (!this.bolsa.isEmpty()) {
            for (Empresa empresa : this.bolsa) {
                System.out.println("Nombre: " + empresa.getNombre());
                System.out.println("Valor actual de acción: " + empresa.getValor());
                System.out.println("Valor anterior de acción: " + empresa.getValorAnt());
                System.out.println("Incremento: " + empresa.getIncremento() + "\n");
            }
        }
        else{
            System.out.println("La bolsa no contiene empresas.");
        }
    }
    public void ActualizarValoresBolsa(){
        for (Empresa empresa : this.bolsa) {
            empresa.setValorAnt(empresa.getValor());
            empresa.setValor(Utilidades.GenerarNumAleat(20000));
            empresa.setIncremento(empresa.getValor()-empresa.getValorAnt());
        }
        System.out.println("Valor de acciones de todas las empresas actualizados.");
    }

}