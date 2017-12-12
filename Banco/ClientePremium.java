package Banco;

public class ClientePremium extends Cliente {

    private GestorDeInversiones gestor;

    public ClientePremium(String nombre, String DNI, Double saldo, GestorDeInversiones gestor){
        super(nombre, DNI, saldo);
        setGestor(gestor);
    }

    public ClientePremium(Cliente cliente, GestorDeInversiones gestor){
        super(cliente.getNombre(), cliente.getDNI(), cliente.getSaldo());
        setGestor(gestor);

    }

    public GestorDeInversiones getGestor() {
        return gestor;
    }

    public void setGestor(GestorDeInversiones gestor) {
        this.gestor = gestor;
    }

    public void mejorarPremium(Cliente cliente, GestorDeInversiones gestor){


    }
}
