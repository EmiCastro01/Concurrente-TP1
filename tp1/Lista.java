package tp1;

import tp1.avion.Asiento;

import java.util.ArrayList;

public class Lista {

    private ArrayList<Asiento> reservasConfirmadas;
    private ArrayList<Asiento> reservasCanceladas;
    private ArrayList<Asiento> reservasPendientesDePago;

    public Lista(){
        reservasCanceladas = new ArrayList<>();
        reservasConfirmadas = new ArrayList<>();
        reservasPendientesDePago = new ArrayList<>();
    }

    public ArrayList<Asiento> getReservasCanceladas() {
        return reservasCanceladas;
    }
    public ArrayList<Asiento> getReservasPendientesDePago() {
        return reservasPendientesDePago;
    }
    public ArrayList<Asiento> getReservasConfirmadas() {
        return reservasConfirmadas;
    }

}
