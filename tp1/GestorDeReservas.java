package tp1;

import tp1.avion.*;

import java.util.ArrayList;

public class GestorDeReservas {

    private Avion avion;


    private ArrayList<Asiento> reservasConfirmadas;
    private ArrayList<Asiento> reservasCanceladas;
    private ArrayList<Asiento> reservasPendientesDePago;

    public GestorDeReservas(Avion avion){
        Avion = avion;
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

    public int getCountReservasCanceladas(){
        return reservasCanceladas.toArray().length;
    }

    public int getCountReservasConfirmadas(){
        return reservasConfirmadas.toArray().length;
    }

    public double getOcupacionTotal(){
        return (double)getCountReservasConfirmadas() / Avion.getCantidadTotalAsientos() * 100 ;
    }

    public int getAsientosTotales(){return  Avion.getCantidadTotalAsientos();}

    public Reserva generarReserva(Integer numeroDeAsiento) {
            return null;
    }


}
