package Banco;

public class ClientePremium extends Cliente {
    //Atributos
    private GestorDeInversiones gestor;

    //Endregion
    //Constructores
    public ClientePremium(String nombre, String DNI, double saldo, GestorDeInversiones gestor){
        super(nombre, DNI, saldo);
        setGestor(gestor);
    }

    public ClientePremium(Cliente cliente, GestorDeInversiones gestor){
        super(cliente.getNombre(), cliente.getDNI(), cliente.getSaldo());
        setGestor(gestor);

    }

    //Endregion
    //Getters y Setters
    public GestorDeInversiones getGestor() {
        return gestor;
    }

    public void setGestor(GestorDeInversiones gestor) {
        this.gestor = gestor;
    }

    //Endregion

}
