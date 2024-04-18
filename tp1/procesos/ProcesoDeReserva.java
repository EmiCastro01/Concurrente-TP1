package tp1.procesos;
import tp1.Lista;
import tp1.avion.Asiento;
import tp1.avion.Avion;
import tp1.avion.Estado;
import java.util.ArrayList;

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

    @Override
    public void run(){





        for(int i = 1; i<=10; i++){
            for(int j = 1; j<=4; j++){
             if(avion.getAsiento(j,i).getEstadoDeAsiento().equals(Estado.LIBRE)){
                 avion.getAsiento(j,i).ocuparAsiento();
                 listas.getReservasPendientesDePago().add(avion.getAsiento(j,i));
             }
            }
        }







    }



}
