package tp1.procesos;

/**
 * La interfaz Proceso define los métodos que deben implementar las clases que representan un proceso en el sistema de reservas.
 * Esta interfaz se utiliza para asegurar que todas las clases de procesos tengan una forma consistente de validar si deben continuar 
 * y de procesar las reservas de manera sincronizada cuando hay concurrencia de procesos.
 */
public interface Proceso {
    /**
     * Valida si el proceso debe continuar.
     * Este método debe ser implementado por las clases que implementen esta interfaz para determinar si el proceso debe continuar o no
     * segun las reglas del proceso.
     * 
     * @return true si el proceso debe continuar, y false de lo contrario.
     */
    boolean validarSiContinua();
    /**
     * Modela la ejecucion de un hilo que representa determinado proceso especificado por la clase que implemente la interfaz.
     * Este método debe ser implementado por las clases que implementen esta interfaz para procesar las reservas de acuerdo 
     * a las reglas de cada proceso.
     */
    void procesar();
}
