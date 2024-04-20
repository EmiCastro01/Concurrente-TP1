package tp1.avion;

import tp1.AsientoEstadoEnum;
import java.util.ArrayList;

public class Avion {

    private ArrayList<Asiento> asientos;
    private Integer numeroDeAsientosPorFila = 10;
    private Integer numeroDeFilas = 4;
   // private Asiento[][] asientos;
    private int CantidadTotalAsientos = 186;


    public Avion() {
       /* asientos = new Asiento[numeroDeAsientosPorFila][numeroDeFilas];
        Integer numero = 0;
        for (int i = 0; i < numeroDeAsientosPorFila; i++) {
            for (int j = 0; j < numeroDeFilas; j++) {
                numero++;
                asientos[i][j] = new Asiento();
                asientos[i][j].setNumeroDeAsiento(numero);
            }
        }

    }
*/      this.asientos = new ArrayList<>();
        for(int i = 1; i < this.CantidadTotalAsientos; i++){
            Asiento a = new Asiento();
            a.setNumeroDeAsiento(i);
            this.asientos.add(a);
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
}
