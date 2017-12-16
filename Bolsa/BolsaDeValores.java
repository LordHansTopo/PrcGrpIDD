package Bolsa;

import General.Escaner;
import General.Utilidades;
import java.io.*;
import java.util.HashSet;

public class BolsaDeValores implements Serializable{
    private HashSet<Empresa> bolsa = new HashSet<>();

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
    public void GuardarCopia(){
        try{
           ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("CopiaBolsa.bin"));
           try{
           output.writeObject(this);
           }
           finally{
               output.close();
           }
        }
        catch(FileNotFoundException fnfex){
            System.out.println("Error al crear el archivo");
        }
        catch(IOException ioex){
            System.out.println("Error de E/S");
        }
    }
    public BolsaDeValores CargarCopia(){
        BolsaDeValores copiaBolsa = null;
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("CopiaBolsa.bin"));
            try {
                try {
                    copiaBolsa = (BolsaDeValores) input.readObject();
                } catch (EOFException eof) {
                    //Fin de fichero
                }
            }
            finally {
                    input.close();
                }
        }
        catch(FileNotFoundException fnfex){
            System.out.println("Error: No existe el archivo CopiaBolsa.bin o no se puede leer.");
        }
        catch(IOException ioex){
            System.out.println("Error de E/S");
        }
        catch (ClassNotFoundException cnfex) {
            System.out.println("Error: Clase no encontrada");
        }
        return copiaBolsa;
    }
}
