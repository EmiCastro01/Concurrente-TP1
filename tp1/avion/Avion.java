package tp1.avion;

import tp1.AsientoEstadoEnum;

public class Avion {

    private Integer numeroDeAsientosPorFila = 10;
    private Integer numeroDeFilas = 4;
    private Asiento[][] asientos;
    private int CantidadTotalAsientos = 186;


    public Avion() {
        asientos = new Asiento[numeroDeAsientosPorFila][numeroDeFilas];
        Integer numero = 0;
        for (int i = 0; i < numeroDeAsientosPorFila; i++) {
            for (int j = 0; j < numeroDeFilas; j++) {
                numero++;
                asientos[i][j] = new Asiento();
                asientos[i][j].setNumeroDeAsiento(numero);
            }
        }

    }

    public  synchronized Asiento getAsiento(Integer fila, Integer columna) {
        return this.asientos[columna - 1][fila - 1];
    }

    public void printAvion() {
        for (int i = 0; i < numeroDeAsientosPorFila; i++) {
            System.out.println("");
            for (int j = 0; j < numeroDeFilas; j++) {
                String numeroAsiento = asientos[i][j].getEstadoDeAsiento() == AsientoEstadoEnum.LIBRE ?
                        String.valueOf(asientos[i][j].getNumeroDeAsiento()) : "-";
                System.out.print("[ " + numeroAsiento + " ] ");
            }
        }
    }

    public Boolean estaCompleto() {
        for (int i = 0; i < numeroDeAsientosPorFila; i++) {

            for (int j = 0; j < numeroDeFilas; j++) {
                if (asientos[i][j].getEstadoDeAsiento().equals(AsientoEstadoEnum.LIBRE)) {
                    return false;
                }
            }

        }
        return true;
    }

    public int getCantidadTotalAsientos() {
        return CantidadTotalAsientos;
    }
}
