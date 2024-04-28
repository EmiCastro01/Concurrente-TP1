package tp1.procesos;

import tp1.AsientoEstadoEnum;
import tp1.EstadoReserva;
import tp1.GestorDeReservas;
import tp1.utils.Logs;

import java.util.ArrayList;
import java.util.Random;

/**
 * La clase ProcesoDePago representa un proceso que realiza pagos de reservas de asientos en un avión.
 * Implementa la interfaz Runnable, lo que significa que puede ser ejecutada por un hilo.
 * A modo de simulación de una situación real, el proceso de pago tiene una funcion para
 * implementar cierta probabilidad de aprobar o rechazar un pago.
 */
public class ProcesoDePago implements Runnable, Proceso {
    private GestorDeReservas gestorDeReservas;

    /**
     * Constructor para la clase ProcesoDePago.
     * Inicializa el proceso con un gestor de reservas.
     * El gestor de reservas es responsable de gestionar las reservas de asientos en el avión
     * y de implementar las funcionalidades vitales del sistema.
     *
     * @param gestorDeReservas El gestor de reservas.
     */
    public ProcesoDePago(GestorDeReservas gestorDeReservas) {
        this.gestorDeReservas = gestorDeReservas;
    }

    /**
     * El método run se invoca cuando se inicia el hilo que ejecuta este proceso.
     * Mientras el método validarSiContinua devuelva verdadero, el proceso seguirá 
     * tratando de realizar pagos.
     * Este método es parte de la interfaz Runnable y es el punto de entrada para el hilo.
     */
    @Override
    public void run() {
        while (validarSiContinua()) {
            procesar();
        }
    }

    /**
     * Genera un booleano con una cierta probabilidad.
     * Este método se utiliza para simular la aprobación o rechazo de un pago aleatorio 
     * con una cierta probabilidad.
     *
     * @param probabilidadDeAprobacion La probabilidad de que el método devuelva verdadero.
     * @return Verdadero con una probabilidad de probabilidadDeAprobacion, falso en caso contrario.
     * @throws IllegalArgumentException si probabilidadDeAprobacion no está entre 0 y 1.
     */
    private boolean generarBooleanoConProbabilidad(double probabilidadDeAprobacion) {
        if (probabilidadDeAprobacion < 0 || probabilidadDeAprobacion > 1) {
            throw new IllegalArgumentException("La probabilidad debe estar entre 0 y 1");
        }

        var random = new Random();
        var valorAleatorio = random.nextDouble();
        return valorAleatorio < probabilidadDeAprobacion;
    }

    /**
     * Verifica si el proceso puede continuar realizando pagos.
     * El proceso puede continuar si el gestor de reservas puede gestionar más asientos libres.
     * Este método es parte de la interfaz Proceso.
     *
     * @return Verdadero si el proceso puede continuar, falso en caso contrario.
     */
    @Override
    public boolean validarSiContinua() {
        return gestorDeReservas.puedoGestionarAsientos();
    }

    /**
     * Realiza el proceso de ejecución de pago.
     * Llama al método confirmarPago del gestor de reservas el cual es el 
     * encargado de la funcionalidad concurrente de la confirmación del pago.
     * Este método es parte de la interfaz Proceso.
     */
    @Override
    public void procesar() {
        gestorDeReservas.confirmarPago();
    }
}