package tp1.procesos;
import tp1.AsientoEstadoEnum;
import tp1.EstadoReserva;
import tp1.GestorDeReservas;
import tp1.Reserva;
import tp1.utils.Logs;

import java.util.ArrayList;
import java.util.Random;

/**
 * La clase ProcesoDeCancelacion modela la ejecucion de un proceso de cancelacion de una reserva.
 * A modo de simulacion de una situacion real de cancelar una reserva confirmada, se define
 * una probabilidad de 10% de cancelacion de reservas.
 */
public class ProcesoDeCancelacion implements Runnable, Proceso {
    private final GestorDeReservas gestorDeReservas;
    /**
     * Constructor de la clase ProcesoDeCancelacion.
     * Este constructor inicializa el objeto GestorDeReservas que se va a utilizar para cancelar las reservas.
     * @param gestorDeReservas El objeto GestorDeReservas que se va a utilizar para cancelar las reservas.
     */
    public ProcesoDeCancelacion(GestorDeReservas gestorDeReservas) {
        this.gestorDeReservas = gestorDeReservas;
    }
    /**
     * Ejecuta el proceso de cancelación de reservas.
     * Este método ejecuta un hilo que trata de cancelar reservas mientras sea posible gestionar asientos del GestorDeReservas.
     */
    @Override
    public void run() {
        while (validarSiContinua()) {
            procesar();
        }
    }
    /**
     * Valida si el proceso de cancelación de reservas debe continuar.
     * Este método devuelve true si hay asientos para gestionar en el GestorDeReservas, y false de lo contrario.
     * @return true si hay asientos para gestionar en el GestorDeReservas, y false de lo contrario.
     */
    @Override
    public boolean validarSiContinua() {
        return gestorDeReservas.puedoGestionarAsientos();
    }

    /**
     * Modela la ejecucion de un proceso de cancelacion de una reserva.
     * Este método de manera aleatoria cancela una reserva en el registro de GestorDeReservas y marca la reserva como cancelada.
     * O caso contrario marca la reserva aleatoria como CHECKED.
     */
    @Override
    public void procesar() {
        gestorDeReservas.cancelarReserva();
    }
}