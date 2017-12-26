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
        return LimiteInf + (LimiteSup-LimiteInf)*r.nextInt();
    }

    public static boolean validarDNI(String DNI) {
        boolean correcto = false;
        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher matcher = pattern.matcher(DNI);
        if (matcher.matches()) {
            String letraDNI = matcher.group(2);
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int index = Integer.parseInt(matcher.group(1));
            index = index % 23;
            String reference = letras.substring(index, index + 1);
            if (reference.equalsIgnoreCase(letraDNI)) {
                correcto = true;
            } else {
                correcto = false;
            }
        } else {
            correcto = false;
        }
        return correcto;

    }
}
