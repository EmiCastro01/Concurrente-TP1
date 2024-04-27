package tp1.avion;

import tp1.AsientoEstadoEnum;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * La clase Avion representa un avión con una lista de asientos.
 * Cada avión tiene una cantidad total de asientos y un bloqueo para manejar 
 * el acceso concurrente a la lista de asientos.
 */
public class Avion {

    private ArrayList<Asiento> asientos;
    private int CantidadTotalAsientos = 186;
    private ReentrantLock lockListadoAsientos;

    /**
     * Constructor para la clase Avion.
     * Inicializa el avión con una cantidad total de asientos y crea todos los asientos.
     */
    public Avion() {
        lockListadoAsientos = new ReentrantLock();
        this.asientos = new ArrayList<>();
        for(int i = 1; i <= this.CantidadTotalAsientos; i++){
            this.asientos.add(new Asiento(i));
        }
    }

    /**
     * Obtiene un asiento específico del avión.
     *
     * @param numeroDeAsiento El número del asiento.
     * @return El asiento.
     */
    public Asiento getAsiento(Integer numeroDeAsiento) {
        return this.asientos.get(numeroDeAsiento);
    }

    /**
     * Verifica si el avión está completo.
     *
     * @return Verdadero si todos los asientos están ocupados, falso en caso contrario.
     */
    public Boolean estaCompleto() {
        for(Asiento a : this.asientos){
            if(a.getEstadoDeAsiento().equals(AsientoEstadoEnum.LIBRE)) return false;
        }
        return true;
    }

    /**
     * Obtiene la cantidad total de asientos.
     *
     * @return La cantidad total de asientos.
     */
    public int getCantidadTotalAsientos() {
        return this.CantidadTotalAsientos;
    }

    /**
     * Obtiene el bloqueo para la lista de asientos.
     * Este bloqueo se utiliza para controlar el acceso a la lista 
     * de asientos donde mas de un proceso interactua con la variable.
     *
     * @return El bloqueo para la lista de asientos.
     */
    public ReentrantLock getLockListadoAsientos() {
        return lockListadoAsientos;
    }
}