package tp1;


import tp1.avion.Asiento;
import tp1.avion.Avion;
import tp1.utils.CalculadoraProbabilidad;
import tp1.utils.Logs;

import java.util.ArrayList;
import java.util.Random;

/**
 * La clase GestorDeReservas se encarga de gestionar las reservas de asientos de un avión.
 * Implementa las funcionalidades mas importantes del sistema.
 * Mantiene registros de reservas pendientes de pago, reservas confirmadas, reservas canceladas y reservas verificadas.
 * Proporciona métodos para generar una reserva, confirmarla, cancelarla o verificarla.
 * 
 * Cada proceso (reserva, pago, cancelación y verificación) tiene un tiempo de ejecución asociado en milisegundos.
 * 
 * La clase también proporciona métodos para obtener los registros de reservas y los tiempos de los procesos, 
 * imprimir las reservas pendientes de pago, obtener el conteo de las reservas en diferentes estados, 
 * obtener la ocupación total del avión, y verificar si se pueden gestionar más asientos.
 */
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

    /**
     * Constructor para la clase GestorDeReservas.
     * Inicializa el avión y los tiempos de los procesos.
     * También inicializa los registros de las reservas.
     *
     * @param milisProcesoReserva El tiempo de ejecución del proceso de reserva en milisegundos.
     * @param milisProcesoPago El tiempo de ejecución del proceso de pago en milisegundos.
     * @param milisProcesoCancelacion El tiempo de ejecución del proceso de cancelación en milisegundos.
     * @param milisProcesoVerificacion El tiempo de ejecución del proceso de verificación en milisegundos.
     */
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

  /**
 * Devuelve la lista de reservas canceladas.
 *
 * @return Una lista de objetos Reserva que representan las reservas canceladas.
 */  
    public ArrayList<Reserva> getReservasCanceladas() {
        return reservasCanceladas;
    }
    /**
 * Devuelve la lista de reservas pendientes de pago.
 *
 * @return Una lista de objetos Reserva que representan las reservas que aún están pendientes de pago.
 */
    public  ArrayList<Reserva> getReservasPendientesDePago() {
        return reservasPendientesDePago;
    }
    /**
 * Devuelve la lista de reservas confirmadas.
 *
 * @return Una lista de objetos Reserva que representan las reservas que han sido confirmadas.
 */
    public ArrayList<Reserva> getReservasConfirmadas() {
        return reservasConfirmadas;
    }
    /**
 * Devuelve la lista de reservas verificadas.
 *
 * @return Una lista de objetos Reserva que representan las reservas que han sido verificadas.
 */
    public ArrayList<Reserva> getReservasVerificadas() {
        return reservasVerificadas;
    }

/**
 * Devuelve el tiempo de ejecución del proceso de reserva en milisegundos.
 *
 * @return El tiempo de ejecución del proceso de reserva en milisegundos.
 */
public int getMilisProcesoReserva() {
    return milisProcesoReserva;
}

/**
 * Devuelve el tiempo de ejecución del proceso de pago en milisegundos.
 *
 * @return El tiempo de ejecución del proceso de pago en milisegundos.
 */
public int getMilisProcesoPago() {
    return milisProcesoPago;
}

/**
 * Devuelve el tiempo de ejecución del proceso de cancelación en milisegundos.
 *
 * @return El tiempo de ejecución del proceso de cancelación en milisegundos.
 */
public int getMilisProcesoCancelacion() {
    return milisProcesoCancelacion;
}

/**
 * Devuelve el tiempo de ejecución del proceso de verificación en milisegundos.
 *
 * @return El tiempo de ejecución del proceso de verificación en milisegundos.
 */
public int getMilisProcesoVerificacion() {
    return milisProcesoVerificacion;
}
/**
 * Imprime los números de asiento de las reservas pendientes de pago.
 */
public void printReservasPendientesDePago() {
    String s = " ";
    for (Reserva r : reservasPendientesDePago) {
        s += " " + r.getAsiento().getNumeroDeAsiento().toString() + " ";
    }
    System.out.println(s);
}
/**
 * Devuelve el número de reservas pendientes de pago.
 *
 * @return El número de reservas pendientes de pago.
 */
public int getCountReservasReservadas() {
    return reservasPendientesDePago.toArray().length;
}
/**
 * Devuelve el número de reservas confirmadas.
 *
 * @return El número de reservas confirmadas.
 */
public int getCountReservasConfirmadas() {
    return reservasConfirmadas.toArray().length;
}

/**
 * Devuelve el número de reservas canceladas.
 *
 * @return El número de reservas canceladas.
 */
public int getCountReservasCanceladas() {
    return reservasCanceladas.toArray().length;
}
/**
 * Devuelve el número de reservas verificadas.
 *
 * @return El número de reservas verificadas.
 */
public int getCountReservasVerificadas() {
    return reservasVerificadas.toArray().length;
}
/**
 * Devuelve la ocupación total del avión como un porcentaje.
 * Se calcula como el número de reservas verificadas dividido por el número total de asientos, multiplicado por 100.
 *
 * @return La ocupación total del avión.
 */
public double getOcupacionTotal() {
    return (double) getCountReservasVerificadas() / avion.getCantidadTotalAsientos() * 100 ;
}
/**
 * Devuelve el número total de asientos en el avión.
 *
 * @return El número total de asientos en el avión.
 */
public int getAsientosTotales() {
    return avion.getCantidadTotalAsientos();
}
/**
 * Genera una reserva para un asiento específico en el avión.
 * Primero intenta tomar el bloqueo del listado de asientos del avion para que ningun
 * otro hilo pueda acceder a la lista de asientos mientras se genera la reserva.
 * Luego, obtiene el bloqueo del asiento a reservar, libera el bloqueo 
 * del listado de asientos del avion y verifica si está libre el asiento.
 * Si el asiento está libre, crea una reserva para el asiento y la agrega 
 * a la lista de reservas pendientes de pago.
 * Despues de añadir la reserva a pendientes de pago, manda a dormir al hilo
 * por un tiempo determinado para simular el proceso de reserva.
 * 
 * @param numeroDeAsiento El número del asiento para el que se va a generar la reserva.
 * @return La reserva generada, o null si el asiento ya está reservado o si ocurre alguna excepción.
 */
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

    /**
     * Confirma el pago de una reserva pendiente.
     * A modo de simulacion de una situacion real se establece una probabilidad de pago de 90%.
     * Primero obtiene una reserva aleatoria de la lista de reservas pendientes de pago.
     * Luego, toma el bloqueo de la reserva y verifica si la reserva está pendiente.
     * Si la reserva está pendiente, genera un valor aleatorio para aprobar o rechazar el pago.
     * Si el pago es aprobado, elimina la reserva de la lista de reservas pendientes de pago y la agrega a la lista de reservas confirmadas.
     * Si el pago es rechazado, elimina la reserva de la lista de reservas pendientes de pago y la agrega a la lista de reservas canceladas.
     * Después de confirmar o cancelar la reserva, manda a dormir al hilo por un tiempo determinado para simular el proceso de pago.
     */
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

/**
 * Cancela una reserva de las confirmadas. 
 * A modo de simulación de una situación real, se establece una probabilidad de cancelar la reserva del 10%.
 * Este método selecciona una reserva aleatoria de la lista de reservas confirmadas que no ha sido 
 * marcada como "checked", y luego decide aleatoriamente si cancelarla o marcarla como "checked".
 * Si es cancelada se borra del registro de reservas confirmadas y se agrega al de reservas canceladas.
 * Y se manda a dormir al hilo por un tiempo determinado para simular el proceso de cancelación.
 * Si no es cancelada se marca como "checked".
 * Y tambien se manda a dormir al hilo por un tiempo determinado para simular el proceso de cancelación.
 * 
 * @throws InterruptedException si el hilo actual es interrumpido mientras está durmiendo.
 */
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


/**
* Verifica una reserva confirmada.
* Primero, toma el bloqueo de lista de reservas confirmadas para que ningún otro hilo 
* pueda acceder a la lista de reservas confirmadas mientras se verifica una reserva.
* Luego, selecciona una reserva aleatoria de la lista de reservas confirmadas que 
* estén marcadas como "checked". Si no hay reservas marcadas como "checked", retorna del proceso.
* Luego, toma el bloqueo de la reserva y verifica si la reserva está marcada como "checked" y si 
* sigue estando en la lista de reservas confirmadas. 
* La borra de la lista de reservas confirmadas y la agrega a la lista de reservas verificadas.
* Después de verificar la reserva, manda a dormir al hilo por un tiempo determinado para simular el proceso de verificación.
* 
*/
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

/**
* Verifica si el gestor de reservas puede gestionar más asientos libres.
* Calcula la cantidad total de asientos en el avión y la compara con la cantidad de reservas procesadas.
* Si la cantidad de reservas procesadas es menor que la cantidad total de asientos, devuelve verdadero.
* En caso contrario, devuelve falso.
*
* @return Verdadero si el gestor de reservas puede gestionar más asientos libres, falso en caso contrario.
*/
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

/**
* Obtiene una reserva aleatoria de una lista de reservas.
* Si la lista de reservas es nula o está vacía, devuelve null.
* De lo contrario, genera un índice aleatorio y devuelve la reserva en ese índice.
*
* @param reservaPendiente La lista de reservas de la que se obtendrá una reserva aleatoria.
* @param <T> El tipo de reserva.
* @return Una reserva aleatoria de la lista de reservas, o null si la lista es nula o está vacía.
*/
    private static <T> T  getReservaAleatorio(ArrayList<T> reservaPendiente) {
        if (reservaPendiente == null || reservaPendiente.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int indiceAleatorio = random.nextInt(reservaPendiente.size());
        return reservaPendiente.get(indiceAleatorio);
    }



}
