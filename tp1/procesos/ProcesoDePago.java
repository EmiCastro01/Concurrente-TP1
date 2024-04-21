package tp1.procesos;
import tp1.AsientoEstadoEnum;
import tp1.EstadoReserva;
import tp1.GestorDeReservas;
import tp1.utils.Logs;

import java.util.ArrayList;
import java.util.Random;

public class ProcesoDePago implements Runnable, Proceso{
    private GestorDeReservas gestorDeReservas;
    public ProcesoDePago(GestorDeReservas gestorDeReservas)
    {
        this.gestorDeReservas = gestorDeReservas;
    }
    @Override
    public void run(){
        while(validarSiContinua()) {
            procesar();
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



    @Override
    public boolean validarSiContinua() {
        return gestorDeReservas.puedoGestionarAsientos();
    }

    @Override
    public void procesar() {
        gestorDeReservas.confirmarPago();
    }

    @Override
    public void esperar() {

    }
}
