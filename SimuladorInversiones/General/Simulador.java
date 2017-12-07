package SimuladorInversiones.General;

public class Simulador {
    //region atributos
    private InterfazDeUsuario interfaz;
    /*
    private BolsaDeValores bolsaDeValores;
    private BancoDeInversiones bancoDeInversiones;
    private AgenteDeInversiones agenteDeInversiones;
    */
    //endregion
    //region Getters & Setters
    public InterfazDeUsuario getInterfaz() {
        return interfaz;
    }

    public void setInterfaz(InterfazDeUsuario interfaz) {
        this.interfaz = interfaz;
    }
    /*
    public BolsaDeValores getBolsaDeValores() {
        return bolsaDeValores;
    }

    public void setBolsaDeValores(BolsaDeValores bolsaDeValores) {
        this.bolsaDeValores = bolsaDeValores;
    }

    public BancoDeInversiones getBancoDeInversiones() {
        return bancoDeInversiones;
    }

    public void setBancoDeInversiones(BancoDeInversiones bancoDeInversiones) {
        this.bancoDeInversiones = bancoDeInversiones;
    }

    public AgenteDeInversiones getAgenteDeInversiones() {
        return agenteDeInversiones;
    }

    public void setAgenteDeInversiones(AgenteDeInversiones agenteDeInversiones) {
        this.agenteDeInversiones = agenteDeInversiones;
    }
    */
    //endregion
    public Simulador(){
        InterfazDeUsuario interfazUsuario = new InterfazDeUsuario();
        setInterfaz(interfazUsuario);
    }
    public void comenzarSimulacion (){
        getInterfaz().iniciarInterfaz();
    }
}
