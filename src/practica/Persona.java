package practica;


public class Persona {
    private String nombre;
    private String DNI;

    public Persona(String nombre, String DNI){
        setNombre(nombre);
        setDNI(DNI);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void printNombre(){
        System.out.println(getNombre());
    }

    public String getDNI() {
        return DNI;
    }

    public int getNumDNI(){
        String DNIsinLetra = getDNI().substring(7);
        return (Integer.parseInt(DNIsinLetra));
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void printDNI(){
        System.out.println(getDNI());
    }
}
