package Banco;

public class ClientePremium extends Cliente {
    //Atributos
    private GestorDeInversiones gestor;

    //Endregion
    //Constructores
    public ClientePremium(String nombre, String DNI, double saldo, GestorDeInversiones Gestor){ //sin uso
        super(nombre, DNI, saldo);
        gestor=Gestor;
    }

    public ClientePremium(Cliente cliente, GestorDeInversiones Gestor){
        super(cliente.getNombre(), cliente.getDNI(), cliente.getSaldo());
        gestor=Gestor;

    }

    //Endregion
    //Getters y Setters
    public GestorDeInversiones getGestor() {
        return gestor;
    }

    //Endregion

}
