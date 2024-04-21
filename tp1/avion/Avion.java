package tp1.avion;

import tp1.AsientoEstadoEnum;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Avion {

    private ArrayList<Asiento> asientos;
    private int CantidadTotalAsientos = 186;

    private ReentrantLock lockListadoAsientos;
    public Avion() {
        lockListadoAsientos = new ReentrantLock();
        this.asientos = new ArrayList<>();
        for(int i = 1; i <= this.CantidadTotalAsientos; i++){
            this.asientos.add(new Asiento(i));
        }
    }
    public Asiento getAsiento(Integer numeroDeAsiento) {
        return this.asientos.get(numeroDeAsiento);
    }

    public Boolean estaCompleto() {
        for(Asiento a : this.asientos){
            if(a.getEstadoDeAsiento().equals(AsientoEstadoEnum.LIBRE)) return false;
        }
        return true;
    }

    public int getCantidadTotalAsientos() {
        return CantidadTotalAsientos;
    }

    public ReentrantLock getLockListadoAsientos() {
        return lockListadoAsientos;
    }
}
