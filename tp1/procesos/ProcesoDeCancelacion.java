package tp1.procesos;
import tp1.AsientoEstadoEnum;
import tp1.EstadoReserva;
import tp1.GestorDeReservas;
import tp1.Reserva;

import java.util.ArrayList;
import java.util.Random;

public class ProcesoDeCancelacion implements Runnable {
    private final GestorDeReservas gestorDeReservas;

    public ProcesoDeCancelacion(GestorDeReservas gestorDeReservas) {
        this.gestorDeReservas = gestorDeReservas;
    }

    @Override
    public void run() {
        boolean conReservasConfirmadas = true;
        while (conReservasConfirmadas) {
            try {
                Thread.sleep(20);
                var cancelado = generarBooleanoConProbabilidad(0.1);
                var reservaAleatoria = obtenerReservaAleatoriaDeConfirmadas();
                if (reservaAleatoria != null) {
                    if (cancelado) {
                        gestorDeReservas.cancelarReserva(reservaAleatoria); // Dentro del GestorDeReservas
                        System.out.println("Hola soy el hilo " + Thread.currentThread().getName() +
                                " y cancelé la reserva del asiento " + reservaAleatoria.getAsiento().getNumeroDeAsiento());
                    } else {
                        gestorDeReservas.marcarComoChecked(reservaAleatoria);
                        System.out.println("Hola soy el hilo " + Thread.currentThread().getName() +
                                " y marqué como 'checked' la reserva del asiento " + reservaAleatoria.getAsiento().getNumeroDeAsiento());
                    }
                } else {
                    conReservasConfirmadas = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean generarBooleanoConProbabilidad(double probabilidad) {
        if (probabilidad < 0 || probabilidad > 1) {
            throw new IllegalArgumentException("La probabilidad debe estar entre 0 y 1");
        }
        var random = new Random();
        return random.nextDouble() < probabilidad;
    }

    private Reserva obtenerReservaAleatoriaDeConfirmadas() {
        ArrayList<Reserva> reservasConfirmadas = new ArrayList<>(gestorDeReservas.getReservasConfirmadas());

        if (!reservasConfirmadas.isEmpty()) {
            Random random = new Random();
            int indiceAleatorio = random.nextInt(reservasConfirmadas.size());

            return reservasConfirmadas.get(indiceAleatorio);
        } else {
            return null;
        }
    }
}