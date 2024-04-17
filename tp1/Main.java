package tp1;
import tp1.avion.Avion;
import tp1.procesos.*;



public class Main {

    public static void main(String[] args){
      Avion avion1 = new Avion();

      //hilos de cancelacion
      Thread cancelacion1 = new Thread(new ProcesoDeCancelacion("C1"));
      Thread cancelacion2 = new Thread(new ProcesoDeCancelacion("C2"));
      Thread cancelacion3 = new Thread(new ProcesoDeCancelacion("C3"));

      //hilos de reserva

      Thread reserva1 = new Thread(new ProcesoDeReserva("R1"));
      Thread reserva2 = new Thread(new ProcesoDeReserva("R2"));
      Thread reserva3 = new Thread(new ProcesoDeReserva("R3"));

      //hilos de pago

      Thread pago1 = new Thread(new ProcesoDePago("P1"));
      Thread pago2 = new Thread(new ProcesoDePago("P2"));

      //hilos de verificacion

      Thread verificacion1 = new Thread(new ProcesoDeVerificacion("V1"));
      Thread verificacion2 = new Thread(new ProcesoDeVerificacion("V2"));







    }
}
