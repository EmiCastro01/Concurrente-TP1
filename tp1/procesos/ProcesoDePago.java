package tp1.procesos;
import tp1.AsientoEstadoEnum;
import tp1.EstadoReserva;
import tp1.GestorDeReservas;
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
        boolean conReservasPendientes = true;
        while(conReservasPendientes) {
            try {
                Thread.sleep(20);
                var reservaAleatoria = gestorDeReservas.removerReservasPendientesDePago();
                if(reservaAleatoria == null){ continue; }
                var aprobado = generarBooleanoConProbabilidad(0.9);
                if (aprobado) {
                    gestorDeReservas.agregarReservasConfirmadas(reservaAleatoria);
                    }
                else
                {
                        reservaAleatoria.getAsiento().setEstadoDeAsiento(AsientoEstadoEnum.DESCARTADO);
                        reservaAleatoria.setEstado(EstadoReserva.CANCELADA);
                        gestorDeReservas.agregarReservasCanceladas(reservaAleatoria);
                }

                conReservasPendientes = gestorDeReservas.getAsientosTotales() != 0;


            }
            catch (IllegalArgumentException | InterruptedException e)
            {
                System.out.println("Hola soy el hilo " + Thread.currentThread() + "y tengo el siguiente error: " + e.getMessage());
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
            throw new IllegalArgumentException("La recerva no puede ser nula o vacío");
        }

        Random random = new Random();
        int indiceAleatorio = random.nextInt(reservaPendiente.size());
        return reservaPendiente.get(indiceAleatorio);
    }

}
