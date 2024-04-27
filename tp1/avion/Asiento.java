package tp1.avion;

import tp1.AsientoEstadoEnum;
import tp1.Reserva;

/**
 * La clase Asiento representa un asiento de un avión.
 * Cada asiento tiene un estado (representado por AsientoEstadoEnum[LIBRE, OCUPADO, DESCARTADO])
 *  y un número de asiento.
 */
public class Asiento {

    private AsientoEstadoEnum estadoDeAsiento;
    private Integer numeroDeAsiento;

    /**
     * Constructor para la clase Asiento.
     * Inicializa el asiento con un número y establece su estado a LIBRE.
     *
     * @param numeroAsiento El número del asiento.
     */
    public Asiento(int numeroAsiento){
        this.numeroDeAsiento = numeroAsiento;
        this.estadoDeAsiento = AsientoEstadoEnum.LIBRE;
    }

    /**
     * Establece el estado del asiento.
     *
     * @param estadoDeAsiento El nuevo estado del asiento.
     */
    public void setEstadoDeAsiento(AsientoEstadoEnum estadoDeAsiento){
        this.estadoDeAsiento = estadoDeAsiento;
    }

    /**
     * Obtiene el estado actual del asiento.
     *
     * @return El estado del asiento.
     */
    public AsientoEstadoEnum getEstadoDeAsiento (){
        return estadoDeAsiento;
    }

    /**
     * Obtiene el número del asiento.
     *
     * @return El número del asiento.
     */
    public Integer getNumeroDeAsiento() {
        return numeroDeAsiento; 
    }
}