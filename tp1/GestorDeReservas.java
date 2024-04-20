package tp1;


import tp1.avion.Asiento;
import tp1.avion.Avion;

import java.util.ArrayList;

public class GestorDeReservas {
    private Avion avion;
    private ArrayList<Reserva> reservasConfirmadas;
    private ArrayList<Reserva> reservasCanceladas;
    private ArrayList<Reserva> reservasPendientesDePago;
    private ArrayList<Reserva> reservasVerificadas;

    public GestorDeReservas(Avion avion){
        this.avion = avion;
        reservasCanceladas = new ArrayList<>();
        reservasConfirmadas = new ArrayList<>();
        reservasPendientesDePago = new ArrayList<>();
        reservasVerificadas = new ArrayList<>();
    }

    public ArrayList<Reserva> getReservasCanceladas() {
        return reservasCanceladas;
    }
    public  ArrayList<Reserva> getReservasPendientesDePago() {
        return reservasPendientesDePago;
    }
    public ArrayList<Reserva> getReservasConfirmadas() {
        return reservasConfirmadas;
    }
    public ArrayList<Reserva> getReservasVerificadas() {
        return reservasVerificadas;
    }

    public void printReservasPendientesDePago(){
        String s = " ";
        for (Reserva r :
             reservasPendientesDePago) {
            s += " " + r.getAsiento().getNumeroDeAsiento().toString() + " ";
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
        return (double)getCountReservasConfirmadas() / avion.getCantidadTotalAsientos() * 100 ;
    }

    public int getAsientosTotales(){return  avion.getCantidadTotalAsientos();}

    public Reserva generarReserva(Integer numeroDeAsiento) {
            return null;
    }


    public void marcarComoChecked(Reserva reservaAleatoria) {
    }

    public void cancelarReserva(Reserva reservaAleatoria) {
    }
}
