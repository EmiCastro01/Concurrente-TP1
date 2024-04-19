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
    private List<Integer> asientosPosibles;
    public ProcesoDeReserva(String nombre, Lista listas, Avion avion){
        this.listas = listas;
        this.avion = avion;
        this.nombre=nombre;

    }




        @Override
        public void run(){
            while (!this.avion.estaCompleto()) {
//                asientosPosibles = IntStream.iterate(0, i -> i + 1)
//                        .limit(185)
//                        .boxed()
//                        .collect(Collectors.toList());
//                Random random = new Random();
//                Reserva reserva;
//
//                while(reserva == null){
//                    Integer indiceAsiento = random.nextInt(asientosPosibles.length);
//                    Integer numeroAsiento = asientosPosibles[indiceAsiento];
//                    reserva = Gestor.GenerarReserva(numeroAsiento);
//                    if(reserva == null){
//                        asientosPosibles.removeByIndex(indiceAsiento);
//                    }
//                }
                random.nextInt(4)
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
