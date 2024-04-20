package tp1;

import tp1.avion.Asiento;

import java.util.ArrayList;

public class GestorDeReservas {

    private ArrayList<Asiento> reservasConfirmadas;
    private ArrayList<Asiento> reservasCanceladas;
    private ArrayList<Asiento> reservasPendientesDePago;
    private int AsientosTotales = 185;

    public GestorDeReservas(){
        reservasCanceladas = new ArrayList<>();
        reservasConfirmadas = new ArrayList<>();
        reservasPendientesDePago = new ArrayList<>();
    }

    public ArrayList<Asiento> getReservasCanceladas() {
        return reservasCanceladas;
    }
    public  ArrayList<Asiento> getReservasPendientesDePago() {
        return reservasPendientesDePago;
    }
    public ArrayList<Asiento> getReservasConfirmadas() {
        return reservasConfirmadas;
    }

    public void printReservasPendientesDePago(){
        String s = " ";
        for (Asiento a :
             reservasPendientesDePago) {
            s += " " + a.getNumeroDeAsiento().toString() + " ";
        }
        System.out.println(s);
    }

    public int getAsientosTotales(){return  AsientosTotales;}

}
