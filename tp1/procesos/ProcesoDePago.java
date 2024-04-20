package tp1.procesos;
import tp1.AsientoEstadoEnum;
import tp1.EstadoReserva;
import tp1.GestorDeReservas;
import tp1.utils.Logs;

import java.util.ArrayList;
import java.util.Random;

public class ProcesoDePago implements Runnable{
    private GestorDeReservas gestorDeReservas;
    public ProcesoDePago(GestorDeReservas gestorDeReservas)
    {
        this.gestorDeReservas = gestorDeReservas;
    }
    @Override
    public void run(){
        boolean conReservasPendientes = gestorDeReservas.puedoGestionarAsientos();
        while(conReservasPendientes) {
            try {
                Thread.sleep(20);

                var reservasPendientesDePago = gestorDeReservas.getReservasPendientesDePago();
                var reservaAleatoria = getReservaPendienteAleatorio(reservasPendientesDePago);
                if (reservaAleatoria == null) {
                    continue;
                }

                var aprobado = generarBooleanoConProbabilidad(0.9);
                reservasPendientesDePago.remove(reservaAleatoria);
                if (aprobado) {

                    gestorDeReservas.getReservasConfirmadas().add(reservaAleatoria);
                    Logs.Log(Thread.currentThread(), "Aprobe el pago del asiento " + reservaAleatoria.getAsiento().getNumeroDeAsiento());

                } else {
                    reservaAleatoria.getAsiento().setEstadoDeAsiento(AsientoEstadoEnum.DESCARTADO);
                    reservaAleatoria.setEstado(EstadoReserva.CANCELADA);
                    gestorDeReservas.getReservasCanceladas().add(reservaAleatoria);
                    Logs.Log(Thread.currentThread(), "Rechace el pago del asiento " + reservaAleatoria.getAsiento().getNumeroDeAsiento());

                }

                conReservasPendientes = gestorDeReservas.puedoGestionarAsientos();


            }
            catch (IllegalArgumentException | InterruptedException e)
            {
                Logs.Log(Thread.currentThread(), "Tengo el siguiente error: " + e.getMessage());
            }
        }
    }

    private boolean generarBooleanoConProbabilidad(double probabilidadDeAprobacion) {
        if (probabilidadDeAprobacion < 0 || probabilidadDeAprobacion > 1) {
            throw new IllegalArgumentException("La probabilidad debe estar entre 0 y 1");
        }

        var random = new Random();
        var valorAleatorio = random.nextDouble();
        return valorAleatorio < probabilidadDeAprobacion;
    }

    public static <T> T  getReservaPendienteAleatorio(ArrayList<T> reservaPendiente) {
        if (reservaPendiente == null || reservaPendiente.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int indiceAleatorio = random.nextInt(reservaPendiente.size());
        return reservaPendiente.get(indiceAleatorio);
    }

}
