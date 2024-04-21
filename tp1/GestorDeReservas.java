package tp1;


import tp1.avion.Asiento;
import tp1.avion.Avion;
import tp1.utils.Logs;

import java.util.ArrayList;

public class GestorDeReservas {
    private Avion avion;
    private final int milisProcesoReserva;
    private final int milisProcesoPago;
    private final int milisProcesoCancelacion;
    private final int milisProcesoVerificacion;
    private ArrayList<Reserva> reservasConfirmadas;
    private ArrayList<Reserva> reservasCanceladas;
    private ArrayList<Reserva> reservasPendientesDePago;
    private ArrayList<Reserva> reservasVerificadas;

    public GestorDeReservas(int milisProcesoReserva, int milisProcesoPago, int milisProcesoCancelacion, int milisProcesoVerificacion){
        this.avion = new Avion();
        this.milisProcesoReserva = milisProcesoReserva;
        this.milisProcesoPago = milisProcesoPago;
        this.milisProcesoCancelacion = milisProcesoCancelacion;
        this.milisProcesoVerificacion = milisProcesoVerificacion;
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

    public int getCountReservasReservadas(){
        return reservasPendientesDePago.toArray().length;
    }
    public int getCountReservasConfirmadas(){
        return reservasConfirmadas.toArray().length;
    }
    public int getCountReservasCanceladas(){
        return reservasCanceladas.toArray().length;
    }

    public int getCountReservasVerificadas(){
        return reservasVerificadas.toArray().length;
    }

    public double getOcupacionTotal(){
        return (double) getCountReservasVerificadas() / avion.getCantidadTotalAsientos() * 100 ;
    }

    public int getAsientosTotales(){return  avion.getCantidadTotalAsientos();}

    public Reserva generarReserva(Integer numeroDeAsiento) {
        Asiento asientoAReservar;
        try {
            avion.getLockListadoAsientos().lock();
            asientoAReservar = this.avion.getAsiento(numeroDeAsiento);
        } finally {
            avion.getLockListadoAsientos().unlock();
        }
        synchronized (asientoAReservar){
            if (asientoAReservar.getEstadoDeAsiento().equals(AsientoEstadoEnum.LIBRE)) {
                Reserva reserva = new Reserva(this.avion.getAsiento(numeroDeAsiento));
                synchronized (reservasPendientesDePago){
                    reservasPendientesDePago.add(reserva);
                    try {
                        Thread.sleep(milisProcesoReserva);
                    } catch (Exception e) {

                    }
                }
                Logs.Log(Thread.currentThread(), "Agregué la reserva a pendientes de pago: " + reserva.getAsiento().getNumeroDeAsiento());
                return reserva;
            } else {
                return null;
            }
        }


    }

    public Avion getAvion(){
        return this.avion;
    }

    public void cancelarReserva(Reserva reserva){
        this.reservasConfirmadas.remove(reserva);
        this.reservasCanceladas.add(reserva);
    }

    public void marcarComoChecked(Reserva reserva){
        reserva.setEstado(EstadoReserva.CHECKED);
    }

    public boolean puedoGestionarAsientos(){
        return getCountReservasCanceladas() + getCountReservasVerificadas() != avion.getCantidadTotalAsientos();
    }
}
