package tp1;


import tp1.avion.Asiento;
import tp1.avion.Avion;

import java.util.ArrayList;
import java.util.Random;

public class GestorDeReservas {
    private Avion avion;
    private ArrayList<Reserva> reservasConfirmadas;
    private ArrayList<Reserva> reservasCanceladas;
    private ArrayList<Reserva> reservasPendientesDePago;

    public GestorDeReservas(Avion avion){
        this.avion = avion;
        reservasCanceladas = new ArrayList<>();
        reservasConfirmadas = new ArrayList<>();
        reservasPendientesDePago = new ArrayList<>();
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

    public Reserva removerReservasPendientesDePago (){
        synchronized (reservasPendientesDePago) {
            var reservaAleatoria = getReservaAleatorio(reservasPendientesDePago);
            if(reservaAleatoria==null){return null;}
            reservasPendientesDePago.remove(reservaAleatoria);
            return reservaAleatoria;
        }
    }

    public void agregarReservasConfirmadas(Reserva reserva) {
        synchronized (reservasConfirmadas) {
            reservasConfirmadas.add(reserva);
            System.out.println("Hola soy el hilo " + Thread.currentThread() + " y aprove el pago del asiento " +
                    reserva.getAsiento().getNumeroDeAsiento());
        }
    }

    public void agregarReservasCanceladas(Reserva reserva) {
        synchronized (reservasCanceladas) {
            reservasCanceladas.add(reserva);
            System.out.println("Hola soy el hilo " + Thread.currentThread() + " y rechace el pago del asiento " +
                    reserva.getAsiento().getNumeroDeAsiento());
        }
    }

    public static Reserva  getReservaAleatorio(ArrayList<Reserva> reservas) {
        if (reservas == null || reservas.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int indiceAleatorio = random.nextInt(reservas.size());
        return reservas.get(indiceAleatorio);
    }

}
