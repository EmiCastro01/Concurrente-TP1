package tp1.procesos;

import tp1.GestorDeReservas;

public class ProcesoDeCancelacion implements Runnable{

    private GestorDeReservas gestorDeReservas;

    public ProcesoDeCancelacion(GestorDeReservas gestorDeReservas){
        this.gestorDeReservas = gestorDeReservas;
    }
    @Override
    public void run(){

    }
}
