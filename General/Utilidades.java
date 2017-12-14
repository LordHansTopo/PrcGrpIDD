package General;

import java.util.Random;

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
}
