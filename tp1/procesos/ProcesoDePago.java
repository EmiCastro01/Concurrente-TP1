package tp1.procesos;
import tp1.GestorDeReservas;
import java.util.ArrayList;
import java.util.Random;

public class ProcesoDePago implements Runnable{

    private String nombre;
    private GestorDeReservas listas;
    public ProcesoDePago(String nombre, GestorDeReservas listas)
    {
        this.nombre=nombre;
        this.listas=listas;
    }
    @Override
    public void run(){
        boolean conReservasPendientes = true;
        while(conReservasPendientes) {
            try {
                Thread.sleep(1000);
            var aprobado = generarBooleanoConProbabilidad(0.9);
            synchronized (listas.getReservasPendientesDePago()) {
                var reservasPendientesDePago = listas.getReservasPendientesDePago();
                var reservaAleatoria = getReservaPendienteAleatorio(reservasPendientesDePago);
                if (aprobado) {
                    synchronized (listas.getReservasConfirmadas()) {
                        reservasPendientesDePago.remove(reservaAleatoria);
                        listas.getReservasConfirmadas().add(reservaAleatoria);
                        System.out.println("Hola soy el hilo " + Thread.currentThread() + " y aprove el pago del asiento " +
                                reservaAleatoria.getNumeroDeAsiento());
                    }
                }
                else
                {
                    System.out.println("Hola soy el hilo " + Thread.currentThread() + " y rechace el pago del asiento " +
                            reservaAleatoria.getNumeroDeAsiento());
                }
                conReservasPendientes = listas.getAsientosTotales() != 0;
            }
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
