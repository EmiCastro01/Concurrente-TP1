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

/**
 * La clase ProcesoDeReserva representa un proceso que realiza reservas de asientos en un avión.
 * Implementa la interfaz Runnable, lo que significa que puede ser ejecutada por un hilo.
 * 
 */
public class ProcesoDeReserva implements Runnable, Proceso {
    private GestorDeReservas gestorDeReservas;
    private List<Integer> asientosPosibles;

    /**
     * Constructor para la clase ProcesoDeReserva.
     * Inicializa el proceso con un gestor de reservas y una lista de asientos posibles a reservar.
     *
     * @param gestorDeReservas El gestor de reservas.
     */
    public ProcesoDeReserva(GestorDeReservas gestorDeReservas) {
        this.gestorDeReservas = gestorDeReservas;
        this.asientosPosibles=new ArrayList<>();
    }

    /**
     * El método run se invoca cuando se inicia el hilo que ejecuta este proceso.
     * Mientras el método validarSiContinua devuelva verdadero, el proceso seguirá realizando reservas.
     */
    @Override
    public void run() {
        while (validarSiContinua()) {
            procesar();
        }
    }

    /**
     * Verifica si el proceso puede continuar realizando reservas.
     * El proceso puede continuar si el gestor de reservas puede gestionar más asientos.
     *
     * @return Verdadero si el proceso puede continuar, falso en caso contrario.
     */
    @Override
    public boolean validarSiContinua() {
        return gestorDeReservas.puedoGestionarAsientos();
    }

    /**
     * Realiza el proceso de reserva.
     * Primero, genera una lista de asientos posibles a reservar.
     * Luego, selecciona un asiento al azar y trata de generar una reserva para ese asiento.
     * Si la reserva no se puede generar (por ejemplo, si el asiento ya está reservado), 
     * el asiento se elimina de la lista de asientos posibles y el proceso se repite.
     */
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