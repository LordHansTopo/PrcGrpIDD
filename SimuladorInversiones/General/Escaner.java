package SimuladorInversiones.General;

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
        int salida = input.nextInt();
        return salida;
    }
}
