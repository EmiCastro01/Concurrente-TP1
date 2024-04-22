package tp1;
import tp1.procesos.ProcesoLogs;
import tp1.avion.Avion;
import tp1.procesos.ProcesoDePago;
import tp1.procesos.ProcesoDeReserva;
import tp1.procesos.ProcesoDeCancelacion;
import tp1.procesos.ProcesoDeVerificacion;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();

        var aleatorio = false;
        var demoraProcesoReserva = aleatorio ? obtenerDemoraParaProceso() : 60;
        var demoraProcesoPagos = aleatorio ? obtenerDemoraParaProceso() : 130;
        var demoraProcesoCancelacion = aleatorio ? obtenerDemoraParaProceso() : 30;
        var demoraProcesoVerificacion = aleatorio ? obtenerDemoraParaProceso() : 80;
        GestorDeReservas gestorReservas = new GestorDeReservas(demoraProcesoReserva, demoraProcesoPagos, demoraProcesoCancelacion, demoraProcesoVerificacion);

        ArrayList<Thread> threads = new ArrayList<Thread>();

        //Hilo Archivo Log

        ProcesoLogs procesoLog = new ProcesoLogs(gestorReservas);
        procesoLog.imprimirTiemposPorProceso();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(procesoLog, 0, 200, TimeUnit.MILLISECONDS);


        //Hilos de reserva
        threads.add(new Thread(new ProcesoDeReserva(gestorReservas), "Reserva1"));
        threads.add(new Thread(new ProcesoDeReserva(gestorReservas), "Reserva2"));
        threads.add(new Thread(new ProcesoDeReserva(gestorReservas), "Reserva3"));
        //Hilos de Pago
        threads.add(new Thread(new ProcesoDePago(gestorReservas), "Pago1"));
        threads.add(new Thread(new ProcesoDePago(gestorReservas), "Pago2"));
        //Hilos de Cancelación
        threads.add(new Thread(new ProcesoDeCancelacion(gestorReservas), "Cancelacion1"));
        threads.add(new Thread(new ProcesoDeCancelacion(gestorReservas), "Cancelacion2"));
        threads.add(new Thread(new ProcesoDeCancelacion(gestorReservas), "Cancelacion3"));
        //Hilos de Verificación
        threads.add(new Thread(new ProcesoDeVerificacion(gestorReservas), "Verificacion1"));
        threads.add(new Thread(new ProcesoDeVerificacion(gestorReservas), "Verificacion2"));


        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("los hilos de reserva fueron interrumpidos");
        }

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        executor.shutdown();

        procesoLog.WriteEndLog((double) timeElapsed / 1000);


    }

    public static int obtenerDemoraParaProceso(){
        Random random = new Random();
        return random.nextInt(40,130);
    }
}
