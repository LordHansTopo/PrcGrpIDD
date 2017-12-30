package General;

import Excepciones.ExcepcionNoNulo;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Escaner {
    private Scanner input;

    public void setInput(Scanner input) {
        this.input = input;
    }

    public Escaner(){
        Scanner escaner = new Scanner(System.in);
        setInput(escaner);
    }
    public int leerInt(){
        while (true) {
            try {
                return input.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Error: Valor introducido inválido, intenta de nuevo:");
                input.next();
            }
        }
    }

    public double leerDouble(){
        while (true) {
            try {
                return input.nextDouble();
            } catch (InputMismatchException ex) {
                System.out.println("Error: Valor introducido inválido, intenta de nuevo:");
                input.next();
            }
        }
    }
    
    public String leerString(){
        while (true) {
            try{
                String leido = input.nextLine();
                if(leido.isEmpty()) throw new ExcepcionNoNulo();
                else return leido;
            } catch (NoSuchElementException ex){
                System.out.println("Error: Valor introducido inválido, intenta de nuevo:");
                input.next();
            }
            catch (ExcepcionNoNulo ex){
                System.out.println("Error: No se ha introducido valor, intenta de nuevo:");
                input.next();
            }
        }
    }
}
