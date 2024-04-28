package tp1.procesos;
import tp1.EstadoReserva;
import tp1.GestorDeReservas;
import tp1.Reserva;
import tp1.utils.Logs;

import java.util.ArrayList;
import java.util.Random;

/**
 * La clase ProcesoDeVerificacion modela la ejecucion del proceso de verificacion de una reserva confirmada.
 * Esta clase ejecuta hilos que van a verificar reservas confirmadas del registro de reservas.
 */
public class ProcesoDeVerificacion implements Runnable, Proceso {
    private GestorDeReservas gestorDeReservas;
    /**
     * Constructor de la clase ProcesoDeVerificacion.
     * Este constructor inicializa el objeto GestorDeReservas que se va a utilizar para verificar las reservas.
     * @param gestorDeReservas El objeto GestorDeReservas que se va a utilizar para verificar las reservas.
     */
    public ProcesoDeVerificacion(GestorDeReservas gestorDeReservas) {
        this.gestorDeReservas = gestorDeReservas;

    }

    /**
     * Ejecuta el proceso de verificación de reservas.
     * Este método ejecuta hilos que verifican reservas mientras sea posible gestionar asientos del GestorDeReservas.
     */
    @Override
    public void run() {
        while (validarSiContinua()) {
            procesar();
        }
    }

    /**
     * Valida si el proceso de verificación de reservas debe continuar.
     * Este método devuelve true si hay reservas para gestionar en el GestorDeReservas, y false de lo contrario.
     * @return true si hay asientos para gestionar en el GestorDeReservas, y false de lo contrario.
     */
    @Override
    public boolean validarSiContinua() {
        return gestorDeReservas.puedoGestionarAsientos();
    }

    /**
     * Modela la ejecucion de un proceso de verificacion de una reserva.
     * Este método verifica una reserva en el registro de GestorDeReservas y manda a dormir al hilo por un tiempo determinado
     * para simular la verificacion de una reserva.
     */
    @Override
    public void procesar() {
        gestorDeReservas.verificarReserva();
    }

}
