package tp1;

import tp1.avion.Asiento;

public class Reserva {
    private EstadoReserva estado;
    private Asiento asiento;

    public  Reserva (Asiento asiento){
        estado = EstadoReserva.PENDIENTE;
        this.asiento = asiento;
    }

    public void setEstado(EstadoReserva estadoReserva){
        estado = estadoReserva;
    }

    public void setAsiento(Asiento asiento){
        this.asiento = asiento;
    }

    public Asiento getAsiento(){
        return asiento;
    }

    public  EstadoReserva getEstado(){
        return  estado;
    }

}
