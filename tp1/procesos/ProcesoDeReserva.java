package tp1.procesos;
import tp1.Lista;
import tp1.avion.Asiento;
import tp1.avion.Avion;
import tp1.avion.Estado;
import java.util.ArrayList;
import java.util.Random;
import tp1.avion.*;

public class ProcesoDeReserva implements Runnable{


    private String nombre;
    private Lista listas;
    private Avion avion;

    public ProcesoDeReserva(String nombre, Lista listas, Avion avion){
        this.listas = listas;
        this.avion = avion;
        this.nombre=nombre;

    }



//H
        @Override
        public void run(){
            while (!this.avion.estaCompleto()) {
                synchronized (this.avion) {
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
            }














    }



}
