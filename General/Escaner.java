package General;

import Excepciones.ExcepcionClientePremium;
import Excepciones.ExcepcionDNIInvalido;
import Excepciones.ExcepcionNoNulo;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Escaner {
    private Scanner input;

    public Escaner(){
        input=new Scanner(System.in);
    }

    public int leerInt(){
        while (true) {
            try {
                return input.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Error: Este valor no es un número entero, intenta de nuevo:");
                input.next();
            }
        }
    }

    public double leerDouble(){
        while (true) {
            try {
                return input.nextDouble();
            } catch (InputMismatchException ex) {
                System.out.println("Error: Este valor no es un número, intenta de nuevo:");
                input.next();
            }
        }
    }
    
    public String leerString(){
        while (true) {
            try{
                input = new Scanner(System.in);
                String leido = input.nextLine();
                if (leido.isEmpty()) throw new ExcepcionNoNulo();
                else return leido;
            }
            catch (ExcepcionNoNulo ex){
                System.out.println("Error: No se ha introducido valor, intenta de nuevo:");
            }
        }
    }

    public String leerDNI(){
        while (true) {
            try{
                input = new Scanner(System.in);
                String DNI = input.nextLine();
                if (DNI.isEmpty()) throw new ExcepcionNoNulo();
                if (!Utilidades.validarDNI(DNI)) throw new ExcepcionDNIInvalido(DNI);
                else return DNI;
            }
            catch (ExcepcionNoNulo ex){
                System.out.println("Error: No se ha introducido valor, intenta de nuevo:");
            }
            catch (ExcepcionDNIInvalido ex){
                System.out.println("Error: El valor introducido (" + ex.getDNIErroneo() + ") no es un DNI o no es válido, intenta de nuevo:");
            }
        }
    }
}
