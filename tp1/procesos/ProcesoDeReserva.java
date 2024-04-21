package tp1.procesos;
import tp1.GestorDeReservas;
import tp1.avion.Avion;
import tp1.Reserva;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ProcesoDeReserva implements Runnable, Proceso {


    private int demoraDelProcesoMilisegundos;
    private GestorDeReservas gestorDeReservas;
    private List<Integer> asientosPosibles;


    public ProcesoDeReserva(GestorDeReservas gestorDeReservas, int demoraDelProcesoMilisegundos) {
        this.demoraDelProcesoMilisegundos = demoraDelProcesoMilisegundos;
        this.gestorDeReservas = gestorDeReservas;
        this.asientosPosibles=new ArrayList<>();

    }


    @Override
    public void run() {
        while (!this.gestorDeReservas.getAvion().estaCompleto()) {
            try{
                Thread.sleep(demoraDelProcesoMilisegundos);

            }catch(Exception e){

            }
            asientosPosibles = IntStream.iterate(0, i -> i + 1)
                    .limit(185)
                    .boxed()
                    .collect(Collectors.toList());
            Random random = new Random();
            Reserva reserva = null;

            while (reserva == null) {
                if(gestorDeReservas.getAvion().estaCompleto()) break;
                Integer indiceAsiento = random.nextInt(asientosPosibles.size());
                Integer numeroAsiento = asientosPosibles.get(indiceAsiento);
                reserva = gestorDeReservas.generarReserva(numeroAsiento);
                if (reserva == null) {
                    asientosPosibles.remove(indiceAsiento);
                }


            }


        }

    }

    @Override
    public boolean validarSiContinua() {
        return false;
    }

    @Override
    public void procesar() {

    }

    @Override
    public void esperar() {

    }
}















