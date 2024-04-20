package tp1.avion;

import tp1.AsientoEstadoEnum;
import tp1.Reserva;

public class Asiento {

    private AsientoEstadoEnum estadoDeAsiento;
    private Integer numeroDeAsiento;

    public Asiento(){
        this.estadoDeAsiento = AsientoEstadoEnum.LIBRE;
    }

    public void setNumeroDeAsiento(int numeroDeAsiento){
        this.numeroDeAsiento = numeroDeAsiento;
    }

    public void setEstadoDeAsiento(AsientoEstadoEnum estadoDeAsiento){
        this.estadoDeAsiento = estadoDeAsiento;
    }
    public AsientoEstadoEnum getEstadoDeAsiento (){
        return estadoDeAsiento;
    }

    public Integer getNumeroDeAsiento() {
        return numeroDeAsiento; 
    }
}
