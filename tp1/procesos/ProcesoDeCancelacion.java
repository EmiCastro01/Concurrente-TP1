package tp1.procesos;
import tp1.AsientoEstadoEnum;
import tp1.EstadoReserva;
import tp1.GestorDeReservas;
import tp1.Reserva;
import tp1.utils.Logs;

import java.util.ArrayList;
import java.util.Random;

public class ProcesoDeCancelacion implements Runnable, Proceso {
    private final GestorDeReservas gestorDeReservas;
    private int demoraDelProcesoMilisegundos;
    public ProcesoDeCancelacion(GestorDeReservas gestorDeReservas, int demoraDelProcesoMilisegundos) {
        this.gestorDeReservas = gestorDeReservas;
        this.demoraDelProcesoMilisegundos = demoraDelProcesoMilisegundos;
    }

    @Override
    public void run() {
        boolean conReservasConfirmadas = gestorDeReservas.puedoGestionarAsientos();
        while (conReservasConfirmadas) {
            try {
                Thread.sleep(demoraDelProcesoMilisegundos);
                var cancelado = generarBooleanoConProbabilidad(0.1);
                var reservaAleatoria = obtenerReservaAleatoriaDeConfirmadas();
                if (reservaAleatoria != null) {
                    if (cancelado) {
                        gestorDeReservas.cancelarReserva(reservaAleatoria); // Dentro del GestorDeReservas
                        Logs.Log(Thread.currentThread(), "Cancelé la reserva del asiento: " + reservaAleatoria.getAsiento().getNumeroDeAsiento());
                    } else {
                        gestorDeReservas.marcarComoChecked(reservaAleatoria);
                        Logs.Log(Thread.currentThread(), "Marqué como 'checked' la reserva del asiento: " + reservaAleatoria.getAsiento().getNumeroDeAsiento());
                    }
                }
                conReservasConfirmadas = gestorDeReservas.puedoGestionarAsientos();
            } catch (Exception e) {
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
        var reservasConfirmadas = gestorDeReservas.getReservasConfirmadas();
        var reservasConfirmadasNoCheckeadas = reservasConfirmadas.stream().filter(p -> p.getEstado() != EstadoReserva.CHECKED).toList();
        if (!reservasConfirmadasNoCheckeadas.isEmpty()) {
            Random random = new Random();
            int indiceAleatorio = random.nextInt(reservasConfirmadasNoCheckeadas.size());

            return reservasConfirmadasNoCheckeadas.get(indiceAleatorio);
        } else {
            return null;
        }
    }

    @Override
    public boolean validarSiContinua() {
        return false;
    }

    @Override
    public void procesar() {

    }

    @Override
    public void esperar() {

    }
}