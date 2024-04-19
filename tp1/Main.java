package tp1;
import tp1.avion.Avion;
import tp1.procesos.ProcesoDeReserva;

import java.util.ArrayList;
import java.util.Random;



public class Main {

    public static void main(String[] args) throws InterruptedException {
      Avion avion1 = new Avion();
        Lista listas = new Lista();


      //hilos de reserva
        String string = "hola";
      Thread reserva1 = new Thread(new ProcesoDeReserva( "hola", listas, avion1));
     Thread reserva2 = new Thread(new ProcesoDeReserva("R2", listas, avion1));
       Thread reserva3 = new Thread(new ProcesoDeReserva("R3", listas, avion1));
        //avion1.printAvion();
      reserva1.start();
      reserva2.start();
      reserva3.start();
     try{
        reserva1.join();
       reserva2.join();
        reserva3.join();

      }catch(InterruptedException e){
        System.out.println("los hilos de reserva fueron interrumpidos");
      }


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

    }
}
