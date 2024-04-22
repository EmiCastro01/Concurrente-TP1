package tp1;


import tp1.avion.Asiento;
import tp1.avion.Avion;
import tp1.utils.CalculadoraProbabilidad;
import tp1.utils.Logs;

import java.util.ArrayList;
import java.util.Random;

public class GestorDeReservas {
    private final Avion avion;
    private final int milisProcesoReserva;
    private final int milisProcesoPago;
    private final int milisProcesoCancelacion;
    private final int milisProcesoVerificacion;
    private final ArrayList<Reserva> reservasConfirmadas;
    private final ArrayList<Reserva> reservasCanceladas;
    private final ArrayList<Reserva> reservasPendientesDePago;
    private final ArrayList<Reserva> reservasVerificadas;

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

    public int getMilisProcesoReserva(){return milisProcesoReserva;}
    public int getMilisProcesoPago(){return milisProcesoPago;}
    public int getMilisProcesoCancelacion(){return milisProcesoCancelacion;}
    public int getMilisProcesoVerificacion(){return milisProcesoVerificacion;}
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
            synchronized (asientoAReservar) {
                avion.getLockListadoAsientos().unlock();
                if (asientoAReservar.getEstadoDeAsiento().equals(AsientoEstadoEnum.LIBRE)) {
                    Reserva reserva = new Reserva(asientoAReservar);
                    synchronized (reservasPendientesDePago) {
                        reservasPendientesDePago.add(reserva);
                        try {
                            Thread.sleep(milisProcesoReserva);
                        } catch (Exception e) {

                        }
                        Logs.Log(Thread.currentThread(), "Agregué la reserva a pendientes de pago: ", reserva.getAsiento().getNumeroDeAsiento());
                    }
                    return reserva;
                } else {
                    return null;
                }
            }
        } catch (Exception ex){
            Logs.Error(Thread.currentThread(), ex.getMessage());
            avion.getLockListadoAsientos().unlock();
            return null;
        }
    }

    public void confirmarPago() {
        try {
            Reserva reservaAleatoria = null;
            synchronized (reservasPendientesDePago) {
                reservaAleatoria = getReservaAleatorio(reservasPendientesDePago);
                if (reservaAleatoria == null) {
                    return;
                }
            }
            synchronized (reservaAleatoria) {
                if (reservaAleatoria.getEstado() != EstadoReserva.PENDIENTE || !reservasPendientesDePago.contains(reservaAleatoria)) {
                    return;
                }
                var aprobado = CalculadoraProbabilidad.generarBooleanoConProbabilidad(0.9);
                synchronized (reservasPendientesDePago) {
                    reservasPendientesDePago.remove(reservaAleatoria);
                }

                if (aprobado) {
                    synchronized (reservasConfirmadas) {
                        reservasConfirmadas.add(reservaAleatoria);
                        Thread.sleep(milisProcesoPago);
                    }
                    Logs.Log(Thread.currentThread(), "Aprobe el pago del asiento ", reservaAleatoria.getAsiento().getNumeroDeAsiento());

                } else {
                    synchronized (reservasCanceladas) {
                        reservaAleatoria.getAsiento().setEstadoDeAsiento(AsientoEstadoEnum.DESCARTADO);
                        reservaAleatoria.setEstado(EstadoReserva.CANCELADA);
                        reservasCanceladas.add(reservaAleatoria);
                        Thread.sleep(milisProcesoPago);
                    }
                    Logs.Log(Thread.currentThread(), "Rechace el pago del asiento ", reservaAleatoria.getAsiento().getNumeroDeAsiento());
                }
            }

        } catch (Exception e) {
            Logs.Error(Thread.currentThread(), e.getMessage());
        }


    }
    public void cancelarReserva() {
        try {
            Reserva reservaAleatoria = null;
            synchronized (reservasConfirmadas) {
                reservaAleatoria = getReservaAleatorio(new ArrayList<>(reservasConfirmadas.stream().filter(p -> p.getEstado() != EstadoReserva.CHECKED).toList()));
                if (reservaAleatoria == null) {
                    return;
                }
            }
            synchronized (reservaAleatoria) {
                if (reservaAleatoria.getEstado() == EstadoReserva.CHECKED || !reservasConfirmadas.contains(reservaAleatoria)) {
                    return;
                }
                var cancelado = CalculadoraProbabilidad.generarBooleanoConProbabilidad(0.1);
                if (cancelado) {
                    synchronized (reservasConfirmadas) {
                        this.reservasConfirmadas.remove(reservaAleatoria);
                        Thread.sleep(milisProcesoCancelacion/2);
                    }
                    synchronized (reservasCanceladas) {
                        this.reservasCanceladas.add(reservaAleatoria);
                        Thread.sleep(milisProcesoCancelacion/2);
                    }
                    Logs.Log(Thread.currentThread(), "Cancelé la reserva del asiento: ", reservaAleatoria.getAsiento().getNumeroDeAsiento());
                } else {
                    reservaAleatoria.setEstado(EstadoReserva.CHECKED);
                    Thread.sleep(milisProcesoCancelacion);
                    Logs.Log(Thread.currentThread(), "Marqué como 'checked' la reserva del asiento: ", reservaAleatoria.getAsiento().getNumeroDeAsiento());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void verificarReserva() {
        try {
            Thread.sleep(milisProcesoVerificacion);
            Reserva reservaAleatoria = null;
            synchronized (reservasConfirmadas) {
                reservaAleatoria = getReservaAleatorio(new ArrayList<>(reservasConfirmadas.stream().filter(p -> p.getEstado() == EstadoReserva.CHECKED).toList()));
                if (reservaAleatoria == null) {
                    return;
                }
            }
            synchronized (reservaAleatoria) {
                if (reservaAleatoria.getEstado() != EstadoReserva.CHECKED || !reservasConfirmadas.contains(reservaAleatoria)) {
                    return;
                }
                synchronized (reservasConfirmadas) {
                    this.reservasConfirmadas.remove(reservaAleatoria);
                    Thread.sleep(milisProcesoVerificacion / 2);
                }
                synchronized (reservasVerificadas) {
                    this.reservasVerificadas.add(reservaAleatoria);
                    Thread.sleep(milisProcesoVerificacion / 2);
                }
                Logs.Log(Thread.currentThread(), "Verifiqué la reserva del asiento ", reservaAleatoria.getAsiento().getNumeroDeAsiento());
            }
        } catch (Exception e) {
            Logs.Error(Thread.currentThread(), "Me cerré " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public boolean puedoGestionarAsientos(){
        //var pendientesPago = getCountReservasReservadas();
        //var countConfirmadas = getCountReservasConfirmadas();
        var countCanceladas = getCountReservasCanceladas();
        var countVerificadas = getCountReservasVerificadas();
        var totalProcesadas =
                //pendientesPago +
                //countConfirmadas +
                countCanceladas +
                countVerificadas;
        var countTotal = avion.getCantidadTotalAsientos();
        if( totalProcesadas != countTotal){
            return true;
        }
        else {
            return false;
        }
    }

    private static <T> T  getReservaAleatorio(ArrayList<T> reservaPendiente) {
        if (reservaPendiente == null || reservaPendiente.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int indiceAleatorio = random.nextInt(reservaPendiente.size());
        return reservaPendiente.get(indiceAleatorio);
    }



}
