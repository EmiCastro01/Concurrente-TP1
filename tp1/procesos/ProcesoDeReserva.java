package tp1.procesos;
import tp1.GestorDeReservas;
import tp1.avion.Avion;
import tp1.Reserva;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ProcesoDeReserva implements Runnable {


    private String nombre;
    private GestorDeReservas gestorDeReservas;
    private List<Integer> asientosPosibles;
    private Avion avion;

    public ProcesoDeReserva(String nombre, GestorDeReservas gestorDeReservas, Avion avion) {
        this.gestorDeReservas = gestorDeReservas;
        this.avion = avion;
        this.nombre = nombre;
        this.asientosPosibles=new ArrayList<>();

    }


    @Override
    public void run() {
        while (!this.avion.estaCompleto()) {
            asientosPosibles = IntStream.iterate(0, i -> i + 1)
                    .limit(185)
                    .boxed()
                    .collect(Collectors.toList());
            Random random = new Random();
            Reserva reserva = null;
            while (reserva == null) {
                Integer indiceAsiento = random.nextInt(asientosPosibles.size());
                Integer numeroAsiento = asientosPosibles.get(indiceAsiento);
                reserva = this.gestorDeReservas.generarReserva(numeroAsiento);
                if (reserva == null) {
                    asientosPosibles.remove(indiceAsiento);
                }
            }
               /* synchronized (this.avion) {
                    Random random = new Random();
                    Integer fila = random.nextInt(4) + 1;
                    Integer columna = random.nextInt(10) + 1;
                    Asiento asiento = this.avion.getAsiento(fila, columna);
                    if (asiento.getEstadoDeAsiento().equals(Estado.LIBRE)) {
                        synchronized (asiento) {
                            asiento.ocuparAsiento();
                            this.listas.getReservasPendientesDePago().add(asiento);
                            System.out.println("hola soy el hilo " + Thread.currentThread() + " y metí el asiento " +
                                    asiento.getNumeroDeAsiento());
                        }
                    }
                }

                */
        }

    }

}















