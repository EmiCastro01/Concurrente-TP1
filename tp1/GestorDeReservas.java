package tp1;


import tp1.avion.Asiento;
import tp1.avion.Avion;

import java.util.ArrayList;

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
            if(this.avion.getAsiento(numeroDeAsiento).getEstadoDeAsiento().equals(AsientoEstadoEnum.LIBRE)){
                Reserva reserva = new Reserva(this.avion.getAsiento(numeroDeAsiento));
                reserva.getAsiento().setEstadoDeAsiento(AsientoEstadoEnum.OCUPADO);
                this.getReservasPendientesDePago().add(reserva);
                try{
                    Thread.sleep(10);

                }catch(Exception e){

                }
                return new Reserva(this.avion.getAsiento(numeroDeAsiento));
            }else{
                return null;
            }
    }

    public Avion getAvion(){
        return this.avion;
    }

//cancelar reserva
    //checked
}
