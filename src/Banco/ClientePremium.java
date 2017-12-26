package Banco;

public class ClientePremium extends Cliente {
    //regionAtributos
    private GestorDeInversiones gestor;

    //endregion
    //region Constructores
    public ClientePremium(String nombre, String DNI, Double saldo, GestorDeInversiones gestor){
        super(nombre, DNI, saldo);
        setGestor(gestor);
    }

    public ClientePremium(Cliente cliente, GestorDeInversiones gestor){
        super(cliente.getNombre(), cliente.getDNI(), cliente.getSaldo());
        setGestor(gestor);

    }

    //endregion
    //regionGetters y Setters
    public GestorDeInversiones getGestor() {
        return gestor;
    }

    public void setGestor(GestorDeInversiones gestor) {
        this.gestor = gestor;
    }

    //endregion
}
