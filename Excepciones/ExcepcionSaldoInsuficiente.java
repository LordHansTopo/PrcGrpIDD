package Excepciones;

public class ExcepcionSaldoInsuficiente extends Exception{
    private double saldoNecesario;

    public ExcepcionSaldoInsuficiente(double SaldoNecesario,String msg){
        super(msg);
        saldoNecesario=SaldoNecesario;
    }

    public double getSaldoNecesario() {
        return saldoNecesario;
    }
}
