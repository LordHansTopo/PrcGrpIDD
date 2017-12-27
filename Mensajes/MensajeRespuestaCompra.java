
package Mensajes;

public class MensajeRespuestaCompra extends MensajeCompra{
    private boolean resultadoOp;
    private int accionesCompradas;
    private double precioAccion,cantidadRestante;

    public boolean isResultadoOp() {
        return resultadoOp;
    }

    public int getAccionesCompradas() {
        return accionesCompradas;
    }

    public double getPrecioAccion() {
        return precioAccion;
    }

    public double getCantidadRestante() {
        return cantidadRestante;
    }

    public MensajeRespuestaCompra(String Cliente, String Empresa,boolean resultado, int compradas, double precioAccion, double restante){
        super();
        cliente=Cliente;
        empresa=Empresa;
        resultadoOp=resultado;
        accionesCompradas=compradas;
        this.precioAccion=precioAccion;
        cantidadRestante=restante;
    }
    public String codificaMensaje(){
        return this.getIdentificador() + "|" + cliente + "|" + empresa + "|" + resultadoOp + "|" + accionesCompradas + "|" +
                precioAccion + "|" + cantidadRestante;
    }

}

//[5004(id)|DNI(nom)|Kokacola(emp)|true/false|2(numAcc)|250(precioAcc)|50(dinero restante)]

