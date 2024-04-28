package tp1.utils;

import java.util.Random;

/**
 * La clase CalculadoraProbabilidad proporciona un método para generar un booleano aleatorio con una probabilidad dada.
 * Este método es útil en simulaciones donde se necesite tomar decisiones aleatorias basadas en ciertas probabilidades.
 */
public class CalculadoraProbabilidad {
    /**
     * Genera un booleano aleatorio con una probabilidad dada.
     * 
     * Este método genera un número aleatorio entre 0 y 1, y luego devuelve true si este número es menor que la probabilidad dada.
     * Por lo tanto, la probabilidad de que este método devuelva true es igual a la probabilidad dada.
     * 
     * @param probabilidadPositivo La probabilidad de que este método devuelva true. Debe estar entre 0 y 1.
     * @return Un booleano aleatorio, que es true con la probabilidad dada y false de lo contrario.
     * @throws IllegalArgumentException si la probabilidad dada no está entre 0 y 1.
     */
    public static boolean generarBooleanoConProbabilidad(double probabilidadPositivo) {
        if (probabilidadPositivo < 0 || probabilidadPositivo > 1) {
            throw new IllegalArgumentException("La probabilidad debe estar entre 0 y 1");
        }

        var random = new Random();
        var valorAleatorio = random.nextDouble();
        return valorAleatorio < probabilidadPositivo;
    }
}
