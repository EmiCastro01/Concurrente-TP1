package tp1.procesos;
import tp1.GestorDeReservas;
import tp1.avion.Avion;
import tp1.Reserva;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ProcesoDeReserva implements Runnable, Proceso {
    private GestorDeReservas gestorDeReservas;
    private List<Integer> asientosPosibles;

    public ProcesoDeReserva(GestorDeReservas gestorDeReservas) {
        this.gestorDeReservas = gestorDeReservas;
        this.asientosPosibles=new ArrayList<>();

    }

    @Override
    public void run() {
        while (validarSiContinua()) {
            procesar();
        }
    }

    @Override
    public boolean validarSiContinua() {
        return gestorDeReservas.puedoGestionarAsientos();
    }

    @Override
    public void procesar() {
        asientosPosibles = IntStream.iterate(0, i -> i + 1)
                .limit(gestorDeReservas.getAsientosTotales())
                .boxed()
                .collect(Collectors.toList());
        Random random = new Random();
        Reserva reserva = null;

        while (reserva == null && validarSiContinua()) {
            Integer indiceAsiento = random.nextInt(asientosPosibles.size());
            Integer numeroAsiento = asientosPosibles.get(indiceAsiento);
            reserva = gestorDeReservas.generarReserva(numeroAsiento);
            if (reserva == null) {
                asientosPosibles.remove(Arrays.asList(numeroAsiento));
            }
        }
    }
}















