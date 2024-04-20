package tp1;
import procesos.ProcesoLogs;
import tp1.avion.Avion;
import tp1.procesos.ProcesoDeReserva;

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

        ArrayList<Thread> threads =  new ArrayList<Thread>();

        Avion avion1 = new Avion();
        tp1.Lista listas = new tp1.Lista(avion1);

        ProcesoLogs procesoLog =  new ProcesoLogs(listas);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(procesoLog, 0, 200, TimeUnit.MILLISECONDS);

        //hilos de reserva
        String string = "hola";
        threads.add(new Thread(new ProcesoDeReserva("hola", listas, avion1)));
        threads.add(new Thread(new ProcesoDeReserva("R2", listas, avion1)));
        threads.add(new Thread(new ProcesoDeReserva("R3", listas, avion1)));



        // avion1.printAvion();

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

        //avion1.printAvion();
        listas.printReservasPendientesDePago();

        // System.out.println(listas.getReservasPendientesDePago());
        //hilos de cancelacion
/*
      Thread cancelacion1 = new Thread(new ProcesoDeCancelacion("C1"));
      Thread cancelacion2 = new Thread(new ProcesoDeCancelacion("C2"));
      Thread cancelacion3 = new Thread(new ProcesoDeCancelacion("C3"));
      //hilos de pago

      Thread pago1 = new Thread(new ProcesoDePago("P1"));
      Thread pago2 = new Thread(new ProcesoDePago("P2"));

      //hilos de verificacion

      Thread verificacion1 = new Thread(new ProcesoDeVerificacion("V1"));
      Thread verificacion2 = new Thread(new ProcesoDeVerificacion("V2"));
      
*/

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        executor.shutdown();

        procesoLog.WriteEndLog((double)timeElapsed / 1000);
    }
}
