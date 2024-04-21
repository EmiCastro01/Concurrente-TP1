package tp1.utils;

import java.util.Random;

public class CalculadoraProbabilidad {
    public static boolean generarBooleanoConProbabilidad(double probabilidadPositivo) {
        if (probabilidadPositivo < 0 || probabilidadPositivo > 1) {
            throw new IllegalArgumentException("La probabilidad debe estar entre 0 y 1");
        }

        var random = new Random();
        var valorAleatorio = random.nextDouble();
        return valorAleatorio < probabilidadPositivo;
    }
}
