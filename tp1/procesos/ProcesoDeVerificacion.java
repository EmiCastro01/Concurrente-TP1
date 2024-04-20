package tp1.procesos;
import tp1.GestorDeReservas;
import tp1.Reserva;
import java.util.ArrayList;
import java.util.Random;

public class ProcesoDeVerificacion implements Runnable {

    private GestorDeReservas gestorDeReservas;

    public ProcesoDeVerificacion(GestorDeReservas gestorDeReservas) {
        this.gestorDeReservas = gestorDeReservas;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(150);
                synchronized (gestorDeReservas.getReservasConfirmadas()) {
                    ArrayList<Reserva> reservasConfirmadas = gestorDeReservas.getReservasConfirmadas();
                    Reserva reservaAleatoria = getReservaConfirmadaAleatoria(reservasConfirmadas);
                    if (reservaAleatoria.getEstado().equals("CHECKED")) {
                        reservasConfirmadas.remove(reservaAleatoria);
                        gestorDeReservas.getReservasVerificadas().add(reservaAleatoria);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public Reserva getReservaConfirmadaAleatoria(ArrayList<Reserva> reservasConfirmadas) {
        if (reservasConfirmadas == null || reservasConfirmadas.isEmpty()) {
            throw new IllegalArgumentException("La lista de reservas confirmadas no puede ser nula o vacía");
        }
        Random random = new Random();
        int indiceAleatorio = random.nextInt(reservasConfirmadas.size());
        return reservasConfirmadas.get(indiceAleatorio);

    }
}
