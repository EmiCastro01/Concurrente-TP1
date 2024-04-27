package tp1;

import tp1.avion.Asiento;

/**
 * La clase Reserva representa una reserva de un asiento en un avión.
 * Cada reserva tiene un estado(PENDIENTE, CHECKED, CANCELADA,) 
 * y un asiento asociado.
 */
public class Reserva {
    private EstadoReserva estado;
    private Asiento asiento;

    /**
     * Constructor para la clase Reserva.
     * Inicializa la reserva de un asiento y establece su estado a 
     * reserva PENDIENTE, y el asiento como OCUPADO.
     *
     * @param asiento El asiento a reservar.
     */
    public  Reserva (Asiento asiento){
        estado = EstadoReserva.PENDIENTE;
        asiento.setEstadoDeAsiento(AsientoEstadoEnum.OCUPADO);
        this.asiento = asiento;
    }

    /**
     * Establece el estado de la reserva.
     *
     * @param estadoReserva El nuevo estado de la reserva.
     */
    public void setEstado(EstadoReserva estadoReserva){
        estado = estadoReserva;
    }

    /**
     * Establece el asiento de la reserva.
     *
     * @param asiento El nuevo asiento de la reserva.
     */
    public void setAsiento(Asiento asiento){
        this.asiento = asiento;
    }

    /**
     * Obtiene el asiento de la reserva.
     *
     * @return El asiento de la reserva.
     */
    public Asiento getAsiento(){
        return asiento;
    }

    /**
     * Obtiene el estado de la reserva.
     *
     * @return El estado de la reserva.
     */
    public  EstadoReserva getEstado(){
        return  estado;
    }
}