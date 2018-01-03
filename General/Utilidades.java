package General;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {
    public static double GenerarNumAleat(double LimiteInf,double LimiteSup){
        Random r = new Random();
        return LimiteInf + (LimiteSup-LimiteInf)*r.nextDouble();
    }
    public static double GenerarNumAleat(double LimiteSup){
        Random r = new Random();
        return LimiteSup*r.nextDouble();
    }

    public static int GenerarIntAleat(int LimiteInf,int LimiteSup){
        Random r = new Random();
        return Integer.max(r.nextInt(LimiteSup)+LimiteInf,r.nextInt(LimiteSup));
    }
    public static boolean validarDNI(String DNI) { //funcion validadora de DNI
        boolean correcto;
        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])"); //comprueba si sigue el patron de 8 numeros seguido de una letra
        Matcher matcher = pattern.matcher(DNI);
        if (matcher.matches()) { //si sigue el patron, entonces comprueba si el numero modulo 23 genera la letra apropiada
            String letraDNI = matcher.group(2);
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int index = Integer.parseInt(matcher.group(1));
            index = index % 23;
            String reference = letras.substring(index, index + 1);
            correcto = reference.equalsIgnoreCase(letraDNI);
        } else {
            correcto = false;
        }
        return correcto;
    }
}
