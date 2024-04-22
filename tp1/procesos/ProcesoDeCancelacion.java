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
    public ProcesoDeCancelacion(GestorDeReservas gestorDeReservas) {
        this.gestorDeReservas = gestorDeReservas;
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
        gestorDeReservas.cancelarReserva();
    }
}